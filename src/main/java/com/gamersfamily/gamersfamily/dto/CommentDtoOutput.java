package com.gamersfamily.gamersfamily.dto;

import lombok.*;

import java.time.LocalDateTime;


@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CommentDtoOutput extends CommentDto {

    private String username;
    private Long id;
    private LocalDateTime updated;

}

