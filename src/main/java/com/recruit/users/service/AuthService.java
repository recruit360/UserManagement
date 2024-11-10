package com.recruit.users.service;

import com.recruit.users.dto.UserLoginDTO;
import com.recruit.users.model.User;
import com.recruit.users.repository.UserRepository;
import com.recruit.users.utils.JwtUtil;

import org.springframework.stereotype.Service;

/*
 * Service for User Authentication
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String authenticateUser(UserLoginDTO loginDTO) {
        User user = new User(); // userRepository.findByUsername(loginDTO.getUsername());
        user.setId(1233L);
        user.setUsername("test");
        user.setPassword("test");
        user.setRole("any");
        if (checkPassword(loginDTO.getPassword(), user.getPassword())) {
            return generateToken(user); // Return a token upon successful authentication
        }
        return null; // authentication failed
    }

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        return rawPassword.equals(hashedPassword); // Placeholder; replace with actual password check
    }

    private String generateToken(User user) {
        // Generate a JWT or token here
        return jwtUtil.generateToken(user.getUsername());
    }
}
