package com.example.anonym.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.anonym.dto.SearchUserRequest;
import com.example.anonym.dto.SearchUserResponse;
import com.example.anonym.dto.UserResponseDTO;
import com.example.anonym.entity.AppUser;
import com.example.anonym.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService {

    @Autowired
    private final AppUserRepository userRepository;

    public SearchUserResponse searchUser(SearchUserRequest request) {
        String searchParameter = request.getSearchParameter();
        List<AppUser> userList = userRepository.findByUsernameContainingOrFullNameContaining(searchParameter, searchParameter);
        List<UserResponseDTO> responseList = new ArrayList<UserResponseDTO>();
        for (AppUser appUser :  userList){
            UserResponseDTO responseUser = new UserResponseDTO();
            responseUser.setUsername(appUser.getUsername());
            responseUser.setFullName(appUser.getFullName());
            responseUser.setGuid(appUser.getGuid());
            responseUser.setEmail(appUser.getEmail());
            responseList.add(responseUser);
        }
        SearchUserResponse response = new SearchUserResponse(responseList);
        for (UserResponseDTO x : responseList){
            System.out.println(x.getUsername());
        }
        return response;
    }
    
}
