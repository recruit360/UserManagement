package com.recruit.users.service;

import com.recruit.users.dto.UserLoginDTO;
import com.recruit.users.model.User;
import com.recruit.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String authenticateUser(UserLoginDTO loginDTO) {
        User user =  new User(); //userRepository.findByUsername(loginDTO.getUsername());
        user.setId(1233L); user.setUsername("test"); user.setPassword("test"); user.setRole("any");
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
        return "Succes with token";
    }
}
