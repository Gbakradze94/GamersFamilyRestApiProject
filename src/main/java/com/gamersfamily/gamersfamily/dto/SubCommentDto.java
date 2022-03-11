package com.gamersfamily.gamersfamily.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCommentDto {

    @Builder
    private static SubCommentDto of(String body, long userId, long commentId, LocalDateTime createdAt) {
        SubCommentDto subCommentDto = new SubCommentDto();
        subCommentDto.body = body;
        subCommentDto.userId = userId;
        subCommentDto.commentId = commentId;
        subCommentDto.createdAt = createdAt;
        return subCommentDto;
    }

    @NotBlank(message = "body must not be null or empty")
    private String body;
    @NotNull
    private long userId;
    @NotNull
    private long commentId;
    @NotNull
    private LocalDateTime createdAt;

}
