package com.example.QuoraApp.adapters;


import com.example.QuoraApp.dto.UserRequestDTO;
import com.example.QuoraApp.dto.UserResponseDTO;
import com.example.QuoraApp.models.User;

public class UserAdapter {

    public static UserResponseDTO toUserRequestDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
