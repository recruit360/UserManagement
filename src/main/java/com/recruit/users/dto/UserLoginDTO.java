package com.recruit.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*
 * Data Transfer Object for User Login
 */
@Data
public class UserLoginDTO {
    @NotBlank(message = "username can not be empty")
    private String username;
    @NotBlank(message = "password can not be empty")
    private String password;
}
