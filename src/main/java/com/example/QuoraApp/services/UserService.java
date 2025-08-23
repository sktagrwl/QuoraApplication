package com.example.QuoraApp.services;


import com.example.QuoraApp.adapters.UserAdapter;
import com.example.QuoraApp.dto.UserRequestDTO;
import com.example.QuoraApp.dto.UserResponseDTO;
import com.example.QuoraApp.models.User;
import com.example.QuoraApp.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FilterOutputStream;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    @Override
    public Mono<UserResponseDTO> createNewUser(UserRequestDTO userRequestDTO) {
        User user = User.builder()
                .userName(userRequestDTO.getUserName())
                .email(userRequestDTO.getEmail())
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .build();

        return userRepository.save(user)
                .map(UserAdapter::toUserRequestDTO)
                .doOnSuccess(success -> System.out.println("Created new user"))
                .doOnError(error -> System.out.println("Not able to create new User" + error));
    }

    @Override
    public Mono<UserResponseDTO> getUserById(String id) {
        return userRepository.findById(id)
                .map(UserAdapter::toUserRequestDTO)
                .doOnSuccess(success -> System.out.println("Found user by id" + id))
                .doOnError(error -> System.out.println("Not able to find User" + error));
    }

    @Override
    public Flux<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().map(UserAdapter::toUserRequestDTO)
                .doOnComplete(() -> System.out.println("Found all users"))
                .doOnError(error -> System.out.println("Not able to find any User" + error));
    }
}
