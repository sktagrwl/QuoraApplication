package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.PostRequestDTO;
import com.example.QuoraApp.dto.PostResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPostService {

    Mono<PostResponseDTO> createPostByUser(PostRequestDTO postRequestDTO);

    Flux<PostResponseDTO> getAllPostByUser(String userId);

    Mono<PostResponseDTO> getPostById(String id);

}
