package com.recruit.users.dto;

import lombok.Data;

/*
 * Data Transfer Object for User
 */
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String role;
    private String jwtToken;
}
