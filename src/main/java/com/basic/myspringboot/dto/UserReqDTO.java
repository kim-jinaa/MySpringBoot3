package com.basic.myspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor //기본생성자
@AllArgsConstructor
@Getter @Setter
public class UserReqDTO {
    private String name;
    private String email;
}
