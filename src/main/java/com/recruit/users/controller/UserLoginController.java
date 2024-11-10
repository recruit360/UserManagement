package com.recruit.users.controller;

import com.recruit.users.dto.UserDTO;
import com.recruit.users.dto.UserLoginDTO;
import com.recruit.users.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
/**
 * UserLoginController to handle all user basic operations
 * login, logout, register,forgot password, reset password etc
 */
public class UserLoginController {

    private final AuthService authService;

    public UserLoginController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login user
     * 
     * @param loginDTO
     * @return ResponseEntity<?>
     */
    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO loginDTO) {
        System.out.println("Request inside controller");
        String token = authService.authenticateUser(loginDTO);
        UserDTO userDTO = new UserDTO();
        userDTO.setJwtToken(token);
        userDTO.setUsername(loginDTO.getUsername());
        userDTO.setEmail("test@gmail.com");
        userDTO.setRole("any");
        userDTO.setId(1233L);
        return token != null ? ResponseEntity.ok(userDTO) : ResponseEntity.status(401).body("Invalid credentials");
    }

    /**
     * Test call to verify controller is running
     * 
     * @return ResponseEntity<String>
     */
    @GetMapping("/verify")
    public ResponseEntity<String> testCall() {
        return ResponseEntity.ok("Controller is running successfully");
    }

}
