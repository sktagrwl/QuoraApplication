package com.example.QuoraApp.controllers;


import com.example.QuoraApp.dto.PostRequestDTO;
import com.example.QuoraApp.dto.PostResponseDTO;
import com.example.QuoraApp.dto.UserRequestDTO;
import com.example.QuoraApp.dto.UserResponseDTO;
import com.example.QuoraApp.services.IPostService;
import com.example.QuoraApp.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    private final IPostService postService;

    @PostMapping
    public Mono<UserResponseDTO> createNewUser(@RequestBody UserRequestDTO userRequestDTO){
        return userService.createNewUser(userRequestDTO)
                .doOnSuccess(success -> System.out.println("User can been created"))
                .doOnError(error -> System.out.println("Unable to create new user : " + error));
    }

    @GetMapping
    public Flux<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers()
                .doOnComplete(() -> System.out.println("Fetched all users"))
                .doOnError(error -> System.out.println("Unable to Fetched all users :" + error));
    }

    @GetMapping("/{id}")
    public Mono<UserResponseDTO> getUserById(@PathVariable String id){
        return userService.getUserById(id)
                .doOnSuccess(success -> System.out.println("Fetched user by id :" + id))
                .doOnError(error -> System.out.println("Not able to fetch user by id" + error));
    }

    @PostMapping("/post")
    public Mono<PostResponseDTO> createPostByUser(@RequestBody PostRequestDTO postRequestDTO){
        return postService.createPostByUser(postRequestDTO)
                .doOnSuccess(success -> System.out.println("Create post for user"))
                .doOnError(error -> System.out.println("Not able to create post for user" + error));
    }

    @GetMapping("/post/{userId}")
    public Flux<PostResponseDTO> getAllPostByUser(@PathVariable String userId){
        return postService.getAllPostByUser(userId)
                .doOnComplete(() -> System.out.println("Fetched all posts by user"))
                .doOnError(error -> System.out.println("Not able to fetch posts by user" + error));
    }

    @GetMapping("/post/{id}")
    public Mono<PostResponseDTO> getPostById(@PathVariable String id){
        return postService.getPostById(id)
                .doOnSuccess(success -> System.out.println("Fetched post by id :" + id))
                .doOnError(error -> System.out.println("Not able to fetch post by id" + error));
    }
}
