package com.recruit.users.controller;

import com.recruit.users.dto.UserDTO;
import com.recruit.users.dto.UserLoginDTO;
import com.recruit.users.model.User;
import com.recruit.users.service.AuthService;

import jakarta.validation.Valid;

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
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO loginDTO) {
        System.out.println("Request inside controller");
        String token = authService.authenticateUser(loginDTO);
        UserDTO userDTO = new UserDTO();
        userDTO.setJWTToken(token);
        userDTO.setUsername(loginDTO.getUsername());
        userDTO.setEmail("test@gmail.com");
        userDTO.setRole("any");
        userDTO.setId(1233L);
        return token != null ? ResponseEntity.ok(userDTO) : ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> testCall() {
        return ResponseEntity.ok("Controller is running successfully");
    }

}
