package com.basic.myspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor //기본생성자
@AllArgsConstructor
@Getter @Setter
public class UserResDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
