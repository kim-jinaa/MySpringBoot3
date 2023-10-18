package com.basic.myspringboot.security.vo;

import com.basic.myspringboot.security.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UserInfoUserDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(UserInfo userInfo) {
        email=userInfo.getEmail();
        password=userInfo.getPassword();
        authorities= Arrays.stream(userInfo.getRoles().split(","))
                .map(roleName -> new SimpleGrantedAuthority(roleName))
                //.map(SimpleGrantedAuthority::new) //더 간단한 람다식
                .collect(toList()); // alt+enter static import
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    } // 권한체크

    @Override
    public String getPassword() {
        return password;
    } //비밀번호 인증체크

    @Override
    public String getUsername() {
        return email;
    } //이메일 인증체크

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}