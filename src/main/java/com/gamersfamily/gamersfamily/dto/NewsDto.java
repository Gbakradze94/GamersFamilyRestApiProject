package com.gamersfamily.gamersfamily.dto;

import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private Long id;

    @NotNull
    @Size(min = 2, message = "Name Should Be At Least 2 Chars")
    private String name;

    @NotEmpty
    private String body;
    private List<Comment> comments;
    private List<Rating> ratings;
}
