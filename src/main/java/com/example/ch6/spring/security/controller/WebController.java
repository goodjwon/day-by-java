package com.example.ch6.spring.security.controller;

import com.example.ch6.spring.security.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {

    private final UserService userService;

    public WebController(UserService userService) {
        this.userService = userService;
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
