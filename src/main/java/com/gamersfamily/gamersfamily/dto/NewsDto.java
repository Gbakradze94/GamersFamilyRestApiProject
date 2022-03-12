package com.gamersfamily.gamersfamily.dto;

import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.Rating;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
@Api(value = "Comment model information")
public class NewsDto {
    private Long id;

    @NotNull
    @Size(min = 2, message = "Name Should Be At Least 2 Chars")
    @ApiModelProperty(value = "News name")
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "News body")
    private String body;
    @ApiModelProperty(value = "News comments")
    private List<Comment> comments;
    @ApiModelProperty(value = "News ratings")
    private List<Rating> ratings;
}
