package com.basic.myspringboot.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false) // nullable 먼저쓰면 에러! unique를 먼저써야함
    private String email;
    
    private String password;
    
    private String roles; // 권한
}
