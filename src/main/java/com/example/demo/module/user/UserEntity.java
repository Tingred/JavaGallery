package com.example.demo.module.user;

import com.example.demo.module.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;


    public UserEntity(String username, String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = Role.ROLE_USER;
    }
}


