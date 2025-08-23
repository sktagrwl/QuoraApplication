package com.example.QuoraApp.services;


import com.example.QuoraApp.adapters.PostAdapter;
import com.example.QuoraApp.dto.PostRequestDTO;
import com.example.QuoraApp.dto.PostResponseDTO;
import com.example.QuoraApp.models.Post;
import com.example.QuoraApp.repositories.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService{

    private final IPostRepository postRepository;

    @Override
    public Mono<PostResponseDTO> createPostByUser(PostRequestDTO postRequestDTO) {
        Post post = Post.builder()
                .userId(postRequestDTO.getUserId())
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .build();

        return postRepository.save(post)
                .map(PostAdapter::toPostResponseDTO)
                .doOnSuccess(success -> System.out.println("Created post successfully"))
                .doOnError(error -> System.out.println("Unable to create post" + error));
    }

    @Override
    public Flux<PostResponseDTO> getAllPostByUser(String userId) {
        return postRepository.findByUserId(userId)
                .map(PostAdapter::toPostResponseDTO)
                .doOnComplete(() -> System.out.println("Fetched all post by User" + userId))
                .doOnError(error -> System.out.println("Not able to find posts by user" + error));
    }

    @Override
    public Mono<PostResponseDTO> getPostById(String id) {
        return postRepository.findById(id)
                .map(PostAdapter::toPostResponseDTO)
                .doOnSuccess(success -> System.out.println("Fetched post by id" + id))
                .doOnError(error -> System.out.println("Not able to find posts by id" + error));
    }
}
