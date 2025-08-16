package com.example.QuoraApp.dto;

import com.example.QuoraApp.models.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LikeResponseDTO {

    private String id;

    private String targetId;

    private TargetType targetType;

    private Boolean isLike;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
