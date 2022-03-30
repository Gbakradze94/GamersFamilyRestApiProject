package com.gamersfamily.gamersfamily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentSimpleDto {
    private long id;

    @NotBlank
    private String body;
}
