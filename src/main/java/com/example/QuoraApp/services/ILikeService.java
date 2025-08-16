package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.LikeRequestDTO;
import com.example.QuoraApp.dto.LikeResponseDTO;
import com.example.QuoraApp.models.TargetType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILikeService {

    public Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);

    public Mono<LikeResponseDTO> countLikesByTargetIdAndTargetType(String targetId, TargetType targetType);

    public Mono<LikeResponseDTO> countDisLikesByTargetIdAndTargetType(String targetId, TargetType targetType);

    public Mono<LikeResponseDTO> toggleLike(String targetId, TargetType targetType, Boolean isLike);
}
