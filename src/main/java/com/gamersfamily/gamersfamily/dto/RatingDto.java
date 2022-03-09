package com.gamersfamily.gamersfamily.dto;

import com.gamersfamily.gamersfamily.model.Rate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    @NotNull
    private Rate rate;
    @NotNull
    private long userId;
    @NotNull
    private long newsId;

}
