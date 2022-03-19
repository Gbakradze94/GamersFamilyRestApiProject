package com.gamersfamily.gamersfamily.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @NotEmpty(message = "Name Should not be empty")
    private String name;
}
