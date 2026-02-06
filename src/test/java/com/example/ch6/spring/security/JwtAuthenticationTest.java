package com.example.ch6.spring.security;

import com.example.ch6.spring.security.controller.AuthenticationRequest;
import com.example.ch6.spring.security.controller.AuthenticationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtAuthenticationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
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
    @DisplayName("2. 유효한 JWT 토큰으로 보호된 리소스에 접근한다")
    public void testAccessProtectedResourceWithValidJwt() {
        // given: First, get a valid token
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
    @DisplayName("3. JWT 토큰 없이 보호된 리소스에 접근 시 403 Forbidden을 받는다")
    public void testAccessProtectedResourceWithoutJwt() {
        // when
        ResponseEntity<String> response = restTemplate.getForEntity("/admin/panel", String.class);

        // then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
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
}
