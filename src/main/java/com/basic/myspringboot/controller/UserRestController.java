package com.basic.myspringboot.controller;

import com.basic.myspringboot.dto.UserReqDTO;
import com.basic.myspringboot.dto.UserResDTO;
import com.basic.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    //post 입력
    @PostMapping
    public UserResDTO saveUser(@RequestBody UserReqDTO userReqDTO){
        return userService.saveUser(userReqDTO);
    }

    //post 1개조회
    @PostMapping("/{id}")
    public UserResDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    //get 전체조회
    @GetMapping
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
