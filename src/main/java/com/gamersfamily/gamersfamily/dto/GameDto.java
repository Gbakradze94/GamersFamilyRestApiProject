package com.gamersfamily.gamersfamily.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    public static final String VALIDATION_MESSAGE_FOR_NAME = "Name of the game cannot be " +
            "empty and it should contain at least one non-whitespace character.";

    private Long id;

    @NotBlank(message = VALIDATION_MESSAGE_FOR_NAME)
    private String name;

    private String description;

    private String background_image;

    private Date released;

    @JsonProperty("genres")
    private Set<CategoryFetchDto> categories;

//    @JsonProperty("tags")
//    private Set<TagsDto> tags;

//    private Set<PlatformsDto> platforms;

}
