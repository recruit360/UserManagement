package com.recruit.users.controller;

import com.recruit.users.dto.UserLoginDTO;
import com.recruit.users.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserLoginController {

    private final AuthService authService;

    @Autowired
    public UserLoginController(AuthService authService) {
        this.authService = authService;
    }

    // Authenticate a user
    @PostMapping("/signin")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO loginDTO) {
        System.out.println("Request inside controller");
        String token = authService.authenticateUser(loginDTO);
        return token != null ? ResponseEntity.ok(token) : ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> testCall() {
        return ResponseEntity.ok("Controller is running successfully");
    }

}
