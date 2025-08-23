package com.example.QuoraApp.services;


import org.springframework.stereotype.Service;

import com.example.QuoraApp.adapters.LikeAdapter;
import com.example.QuoraApp.dto.LikeRequestDTO;
import com.example.QuoraApp.dto.LikeResponseDTO;
import com.example.QuoraApp.models.Like;
import com.example.QuoraApp.models.TargetType;
import com.example.QuoraApp.repositories.ILikeRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LikeService implements ILikeService{

    private final ILikeRepository likeRepository;

    @Override
    public Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO){
        Like like = LikeAdapter.toLike(likeRequestDTO);
        return likeRepository.save(like)
                .map(LikeAdapter::toLikeResponseDTO)
                .doOnSuccess(response -> System.out.println("Like created successfully" + response))
                .doOnError(error -> System.out.println("Error creating like" + error));
    }

    @Override
    public Mono<LikeResponseDTO> countLikesByTargetIdAndTargetType(String targetId, TargetType targetType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countLikesByTargetIdAndTargetType'");
    }

    @Override
    public Mono<LikeResponseDTO> countDisLikesByTargetIdAndTargetType(String targetId, TargetType targetType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countDisLikesByTargetIdAndTargetType'");
    }

    @Override
    public Mono<LikeResponseDTO> toggleLike(String targetId, TargetType targetType, Boolean isLike) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toggleLike'");
    }

}
