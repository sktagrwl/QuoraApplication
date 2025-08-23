package com.example.QuoraApp.services;

import com.example.QuoraApp.dto.UserRequestDTO;
import com.example.QuoraApp.dto.UserResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<UserResponseDTO> createNewUser(UserRequestDTO userRequestDTO);

    Mono<UserResponseDTO> getUserById(String id);

    Flux<UserResponseDTO> getAllUsers();
}
