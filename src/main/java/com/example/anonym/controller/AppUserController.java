package com.example.anonym.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.anonym.dto.SearchUserRequest;
import com.example.anonym.dto.SearchUserResponse;
import com.example.anonym.service.AppUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AppUserController {
    
    @Autowired
    private final AppUserService appUserService;

    @PostMapping("/api/v1/searchUser")
    public ResponseEntity<SearchUserResponse> searchUser(
        @RequestBody SearchUserRequest request){
        
            SearchUserResponse response = appUserService.searchUser(request);
            return ResponseEntity.ok(response);
    }
 

}
