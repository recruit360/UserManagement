package com.recruit.users.model;

import jakarta.persistence.*;
import lombok.Data;

/*
 * User Entity
 */
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @Column(name = "hashed_password")
    private String password;

    private String role;
}
