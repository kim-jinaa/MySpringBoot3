package com.basic.myspringboot.controller;

import com.basic.myspringboot.dto.UserReqDTO;
import com.basic.myspringboot.dto.UserResDTO;
import com.basic.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    //post 입력
    @PostMapping
    public UserResDTO saveUser(@RequestBody UserReqDTO userReqDTO){
        return userService.saveUser(userReqDTO);
    }

    //post 1개조회
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')") //userboot로 로그인할 경우에만 볼 수 있음(다른 아이디로 로그인 시 403에러)
    public UserResDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    //get 전체조회
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //adminboot로 로그인할 경우에만 볼 수 있음(다른 아이디로 로그인 시 403에러)
    public List<UserResDTO> getUsers(){
        return userService.getUsers();
    }

    //patch 수정
    @PatchMapping("/{email}")
    public UserResDTO updateUser(@PathVariable String email, @RequestBody UserReqDTO userReqDTO){
        return userService.updateUser(email, userReqDTO);
    }
    
    //delete 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(id + "번 User가 삭제처리 되었습니다.");
    }


}
