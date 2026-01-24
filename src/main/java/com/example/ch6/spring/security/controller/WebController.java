package com.example.ch6.spring.security.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {
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
        return "<h1>User Dashboard</h1><p>Welcome, " + principal.getName() +
                "!</p><p>This page is for users with 'USER' role.</p>";
    }

    @GetMapping("/admin/panel")
    public String adminPanel(Principal principal) {
        return "<h1>Admin Panel</h1><p>Welcome, " + principal.getName() +
                "!</p><p>This page is for users with 'ADMIN' role.</p>";
    }

    @GetMapping("/secure/method")
    @PreAuthorize( "hasRole('ADMIN')")
    public String secureMethod() {
        return "<h1>Secure Method</h1><p>This is a page protected by method-level security." +
                " Only 'ADMIN' can see this.</p>";
    }
}
