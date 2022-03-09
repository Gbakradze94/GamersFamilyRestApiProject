package com.gamersfamily.gamersfamily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCommentDto {
    @NotBlank(message="body must not be null or empty")
    private String body;
    @NotNull
    private long userId;
    @NotNull
    private long commentId;
    @NotNull
    private LocalDateTime createdAt;

}
