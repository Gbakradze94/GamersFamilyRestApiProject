package com.gamersfamily.gamersfamily.dto;

import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.model.User;
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
    private static RatingDto of(Rate rate, User userId, News newsId, Game gameId) {
        RatingDto dto = new RatingDto();
        dto.rate = rate;
        dto.news = newsId;
        dto.user = userId;
        dto.game = gameId;
        return dto;
    }

    @NotNull
    private Rate rate;
    @NotNull
    private User user;

    private News news;

    private Game game;

}
