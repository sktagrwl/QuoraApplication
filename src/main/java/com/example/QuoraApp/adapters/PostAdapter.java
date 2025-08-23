package com.example.QuoraApp.adapters;

import com.example.QuoraApp.dto.PostResponseDTO;
import com.example.QuoraApp.models.Post;

public class PostAdapter {

    public static PostResponseDTO toPostResponseDTO(Post post){
        return PostResponseDTO.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
