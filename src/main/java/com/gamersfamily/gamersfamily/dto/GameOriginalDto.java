package com.gamersfamily.gamersfamily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameOriginalDto {

    public static final String VALIDATION_MESSAGE_FOR_NAME = "Name of the game cannot be " +
            "empty and it should contain at least one non-whitespace character.";

    private Long id;

    @NotBlank(message = VALIDATION_MESSAGE_FOR_NAME)
    private String name;

    private String description;

    private String background_image;

    private Date released;

    private Set<CategoryFetchDto> categories;

    private Set<TagsDto> tags;

    private Set<PlatformDto> platforms;

    private List<RatingDto> ratings;
}
