package com.example.QuoraApp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "User Name is required cannot be blank")
    @Size(min = 2 ,max = 40)
    private String userName;

    @NotBlank(message = "First Name is required cannot be blank")
    @Size(min = 1 ,max = 40)
    private String firstName;

    @NotBlank(message = "Last Name is required cannot be blank")
    @Size(min = 1 ,max = 40)
    private String lastName;

    @NotBlank(message = "Email is required cannot be blank")
    @Size(min = 1 ,max = 40)
    private String email;


}
