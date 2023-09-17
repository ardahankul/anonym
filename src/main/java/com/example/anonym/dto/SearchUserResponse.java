package com.example.anonym.dto;
import java.util.List;
import com.example.anonym.entity.AppUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchUserResponse {
    private List<UserResponseDTO> userList;
}
