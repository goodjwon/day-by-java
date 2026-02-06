package com.example.ch6.spring.security.controller;

import com.example.ch6.spring.security.service.JpaUserDetailsService;
import com.example.ch6.spring.security.service.UserService;
import com.example.ch6.spring.security.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class WebController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JpaUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public WebController(UserService userService, AuthenticationManager authenticationManager, JpaUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        try {
            userService.registerUser(username, password);
            return "User '" + username + "' registered successfully!";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/")
    public String home() {
        return "<h1>Welcome</h1><p>This is the public home page.</p>";
    }

    @GetMapping("/home")
    public String userHome() {
        return "<h1>Welcome Home</h1><p>This is the public home page after login.</p>";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(Principal principal) {
        return "<h1>User Dashboard</h1><p>Welcome, " + principal.getName() + "!</p><p>This page is for users with 'USER' role.</p>";
    }

    @GetMapping("/admin/panel")
    public String adminPanel(Principal principal) {
        return "<h1>Admin Panel</h1><p>Welcome, " + principal.getName() + "!</p><p>This page is for users with 'ADMIN' role.</p>";
    }

    @GetMapping("/secure/method")
    @PreAuthorize("hasRole('ADMIN')")
    public String secureMethod() {
        return "<h1>Secure Method</h1><p>This is a page protected by method-level security. Only 'ADMIN' can see this.</p>";
    }
}
