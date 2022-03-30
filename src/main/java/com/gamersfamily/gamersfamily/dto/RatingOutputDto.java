package com.gamersfamily.gamersfamily.dto;

import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.utils.enums.Rate;
import lombok.*;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RatingOutputDto extends RatingDto{
    private String username;
    private long id;
    public RatingOutputDto(long id, String username, Rate rate, User userId, News newsId, Game gameId){
        super(rate,userId,newsId, gameId);
        this.id=id;
        this.username=username;
    }
}
