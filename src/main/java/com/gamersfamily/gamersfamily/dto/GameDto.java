package com.gamersfamily.gamersfamily.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    public static final String VALIDATION_MESSAGE_FOR_DESCRIPTION = "Game Description cannot be " +
            "empty and it should contain at least one non-whitespace character.";

    public static final String VALIDATION_MESSAGE_FOR_NAME = "Name of the game cannot be " +
            "empty and it should contain at least one non-whitespace character.";

    private Long id;

    @NotBlank(message = VALIDATION_MESSAGE_FOR_NAME)
    private String name;

    @NotBlank(message = VALIDATION_MESSAGE_FOR_DESCRIPTION)
    private String description;
}
