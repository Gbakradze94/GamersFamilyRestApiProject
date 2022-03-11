package com.gamersfamily.gamersfamily.dto;

import com.gamersfamily.gamersfamily.utils.enums.Rate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    @Builder
    private static RatingDto of(Rate rate, long userId, long newsId) {
        RatingDto dto = new RatingDto();
        dto.rate = rate;
        dto.newsId = newsId;
        dto.userId = userId;
        return dto;
    }

    @NotNull
    private Rate rate;
    @NotNull
    private long userId;
    @NotNull
    private long newsId;

}
