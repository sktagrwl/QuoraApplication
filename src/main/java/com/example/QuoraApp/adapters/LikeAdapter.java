package com.example.QuoraApp.adapters;

import com.example.QuoraApp.dto.LikeRequestDTO;
import com.example.QuoraApp.dto.LikeResponseDTO;
import com.example.QuoraApp.models.Like;

public class LikeAdapter {

    public static LikeResponseDTO toLikeResponseDTO(Like like){
        return LikeResponseDTO.builder()
                .id(like.getId())
                .targetId(like.getTargetId())
                .targetType(like.getTargetType())
                .isLike(like.getIsLike())
                .build();
    }

    public static Like toLike(LikeRequestDTO likeRequestDTO){
        return Like.builder()
                .targetId(likeRequestDTO.getTargetId())
                .targetType(likeRequestDTO.getTargetType())
                .isLike(likeRequestDTO.getIsLike())
                .build();
    }
}
