package com.gamersfamily.gamersfamily.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsCreateDto {

    private Long id;

    @NotNull
    @Size(min = 2, message = "Name Should Be At Least 2 Chars")
    private String name;

    @NotEmpty
    private String body;
}

