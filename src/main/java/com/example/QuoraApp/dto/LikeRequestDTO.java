package com.example.QuoraApp.dto;

import com.example.QuoraApp.models.TargetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LikeRequestDTO {

    @NotBlank(message = "Target Id is required")
    private String targetId;

    @NotBlank(message = "Target Type is required")
    private TargetType targetType;

    @NotNull(message = "is Like is required")
    private Boolean isLike;
}
