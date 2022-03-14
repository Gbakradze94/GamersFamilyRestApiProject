package com.gamersfamily.gamersfamily.dto;

import lombok.*;

import java.time.LocalDateTime;


@ToString(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentDtoOutput extends CommentDto {

    private String username;
    private long id;
    private LocalDateTime updated;

}

