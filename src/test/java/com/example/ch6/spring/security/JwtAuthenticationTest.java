package com.example.ch6.spring.security;

import com.example.ch6.spring.security.controller.AuthenticationRequest;
import com.example.ch6.spring.security.controller.AuthenticationResponse;
import com.example.ch6.spring.security.service.JpaUserDetailsService;
import com.example.ch6.spring.security.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JwtAuthenticationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Test
    @Order(1)
    @DisplayName("1. 로그인 성공 시 JWT 토큰을 발급받는다")
    public void testLoginAndGetJwt() {
        // given
        AuthenticationRequest request = new AuthenticationRequest("admin", "password");

        // when
        ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity("/api/login", request, AuthenticationResponse.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getJwt());
        assertFalse(response.getBody().getJwt().isEmpty());
    }

    @Test
    @Order(2)
    @DisplayName("2. 유효한 JWT 토큰으로 보호된 리소스에 접근한다 (Admin)")
    public void testAccessProtectedResourceWithValidJwt() {
        // given: First, get a valid token for admin
        AuthenticationRequest loginRequest = new AuthenticationRequest("admin", "password");
        ResponseEntity<AuthenticationResponse> loginResponse = restTemplate.postForEntity("/api/login", loginRequest, AuthenticationResponse.class);
        String token = loginResponse.getBody().getJwt();

        // when: Create headers with the token and make the request
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/admin/panel", HttpMethod.GET, entity, String.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Admin Panel"));
    }

    @Test
    @Order(3)
    @DisplayName("3. JWT 토큰 없이 보호된 리소스에 접근 시 403 Forbidden을 받는다")
    public void testAccessProtectedResourceWithoutJwt() {
        // when
        ResponseEntity<String> response = restTemplate.getForEntity("/admin/panel", String.class);

        // then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(4)
    @DisplayName("4. 유효하지 않은 JWT 토큰으로 보호된 리소스에 접근 시 403 Forbidden을 받는다")
    public void testAccessProtectedResourceWithInvalidJwt() {
        // given
        String invalidToken = "this.is.an.invalid.token";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(invalidToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // when
        ResponseEntity<String> response = restTemplate.exchange("/admin/panel", HttpMethod.GET, entity, String.class);

        // then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    @Order(5)
    @DisplayName("5. 생성된 JWT 토큰을 파싱하여 내용(Claims)을 검증한다")
    public void testDecodeJwtAndVerifyClaims() {
        // given: Load admin user and generate a token
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        String token = jwtUtil.generateToken(userDetails);
        System.out.println("Generated Token: " + token);

        // when: Extract claims from the token
        String username = jwtUtil.extractUsername(token);
        List<String> roles = jwtUtil.extractClaim(token, claims -> claims.get("roles", List.class));
        java.util.Date expiration = jwtUtil.extractExpiration(token);

        // then: Print and verify the claims
        System.out.println("\n--- Decoded JWT Payload ---");
        System.out.println("sub (Subject/Username): " + username);
        System.out.println("roles (Authorities): " + roles);
        System.out.println("exp (Expiration Time): " + expiration);
        System.out.println("---------------------------\n");

        assertEquals("admin", username);
        assertTrue(roles.contains("ROLE_ADMIN"));
        assertTrue(roles.contains("ROLE_USER"));
        assertTrue(expiration.after(new java.util.Date()));
    }

    @Test
    @Order(6)
    @DisplayName("6. USER 역할 사용자의 권한을 테스트한다")
    public void testUserRoleAccess() {
        // given: Register a new user
        HttpHeaders registerHeaders = new HttpHeaders();
        registerHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        org.springframework.util.MultiValueMap<String, String> map = new org.springframework.util.LinkedMultiValueMap<>();
        map.add("username", "newuser");
        map.add("password", "password");
        HttpEntity<org.springframework.util.MultiValueMap<String, String>> registerRequest = new HttpEntity<>(map, registerHeaders);
        restTemplate.postForEntity("/register", registerRequest, String.class);

        // and: log in as the new user
        AuthenticationRequest loginRequest = new AuthenticationRequest("newuser", "password");
        ResponseEntity<AuthenticationResponse> loginResponse = restTemplate.postForEntity("/api/login", loginRequest, AuthenticationResponse.class);
        String token = loginResponse.getBody().getJwt();

        // when: Access user and admin dashboards
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> userResponse = restTemplate.exchange("/user/dashboard", HttpMethod.GET, entity, String.class);
        ResponseEntity<String> adminResponse = restTemplate.exchange("/admin/panel", HttpMethod.GET, entity, String.class);

        // then
        assertEquals(HttpStatus.OK, userResponse.getStatusCode());
        assertTrue(userResponse.getBody().contains("User Dashboard"));
        assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
    }

    @Test
    @Order(7)
    @DisplayName("7. 만료된 JWT 토큰으로 접근 시 403 Forbidden을 받는다")
    public void testExpiredJwtToken() throws InterruptedException {
        // given: Generate a token that expires in 1 millisecond
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        String expiredToken = jwtUtil.generateTokenWithExpiration(userDetails, 1);

        // when: Wait for the token to expire and then use it
        Thread.sleep(5); // Wait 5ms to ensure expiration
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(expiredToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("/admin/panel", HttpMethod.GET, entity, String.class);

        // then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
