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
    public RatingOutputDto(long id, String username, Rate rate, long userId, long newsId){
        super(rate,userId,newsId);
        this.id=id;
        this.username=username;
    }
}
