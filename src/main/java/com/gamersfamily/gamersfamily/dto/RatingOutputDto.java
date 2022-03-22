package com.gamersfamily.gamersfamily.dto;

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
    public RatingOutputDto(long id, String username, Rate rate, long userId, long newsId, long gameId){
        super(rate,userId,newsId, gameId);
        this.id=id;
        this.username=username;
    }
}
