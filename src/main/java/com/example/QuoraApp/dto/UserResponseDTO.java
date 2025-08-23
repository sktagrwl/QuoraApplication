package com.example.QuoraApp.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String userName;

    private String id;

    private String firstName;

    private String lastName;

    private  String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
