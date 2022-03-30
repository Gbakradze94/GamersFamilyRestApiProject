package com.gamersfamily.gamersfamily.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentOriginalDto {

    private long id;

    @NotBlank
    private String body;

    @NotNull
    @JsonIgnore
    private User author;

    @NotNull
    @JsonIgnore
    private News news;

    private LocalDateTime createdAt = LocalDateTime.now();

}
