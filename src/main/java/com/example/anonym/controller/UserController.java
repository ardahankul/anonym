package com.example.anonym.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.anonym.service.UserService;

@RestController
@RequestMapping()
public class UserController{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello endpoint");
    }

    @GetMapping("/getUser")
    public String getUser(){
        return "Get User";
    }

    @GetMapping("/login")
    public String login(){
        return userService.login();
    }

    @PostMapping("/register")
    public String register(){
        return userService.register();
    }

}