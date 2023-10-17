package com.basic.myspringboot.controller;

import com.basic.myspringboot.dto.UserReqDTO;
import com.basic.myspringboot.dto.UserReqForm;
import com.basic.myspringboot.dto.UserResDTO;
import com.basic.myspringboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/userspage")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/first")
    public String leaf(Model model) {
        model.addAttribute("name","스프링부트");
        return "leaf";
    }

    @GetMapping("/index")
    public ModelAndView index(){
        List<UserResDTO> userResDTOList = userService.getUsers();
        return new ModelAndView("index","users",userResDTOList);
    }

    //등록 페이지를 호출해주는 메서드
    @GetMapping("/signup")
    public String showSignUpForm(UserReqDTO user) {
        return "add-user";
    }
    //입력항목 검증을 하고, 등록처리를 해주는 메서드
    @PostMapping("/adduser")
    public String addUser(@Valid UserReqDTO user, BindingResult result, Model model) {
        //입력항목 검증 오류가 발생했나요?
        if (result.hasErrors()) {
            return "add-user";
        }
        //등록요청
        userService.saveUser(user);
        //model.addAttribute("users", userService.getUsers());
        //return "index"; //@GetMapping("/index") 에서도 함
        return "redirect:/userspage/index";
    }

    //수정 페이지를 호출해주는 메서드
    @GetMapping("/edit/{id}")
    public String showUpdateFrom(@PathVariable Long id, Model model){
        UserResDTO userResDTO = userService.getUserById(id);
        model.addAttribute("user", userResDTO);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid UserReqForm user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(">>>> hasErrors user " + user);
            model.addAttribute("user", user);
            //user.setId(id);
            return "update-user";
            //return "redirect:/userspage/edit/{id}(id=${user.id})";
        }
        userService.updateUserForm(user);
        //userService
        return "redirect:/userspage/index";
    }



}
