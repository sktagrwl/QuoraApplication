package com.example.QuoraApp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO {

    @NotBlank(message = "User ID is needed")
    private String userId;

    @NotBlank(message = "Title is needed")
    @Size(min = 1, max = 100)
    private String title;

    @NotBlank(message = "Content is needed")
    @Size(min = 1, max = 1000)
    private String content;

}
