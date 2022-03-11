package com.gamersfamily.gamersfamily.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @Builder
    private static CommentDto of( String body, long userId, long newsId, LocalDateTime createdAt) {
        CommentDto dto = new CommentDto();
        dto.body = body;
        dto.userId = userId;
        dto.newsId = newsId;
        dto.createdAt = createdAt;
      return dto;
    }

    @NotBlank(message = "body must not be null or empty")
    private String body;
    @NotNull
    private long userId;
    @NotNull
    private long newsId;
    @NotNull
    private LocalDateTime createdAt;

}
