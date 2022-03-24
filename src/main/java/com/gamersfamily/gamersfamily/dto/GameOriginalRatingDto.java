package com.gamersfamily.gamersfamily.dto;

import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.repository.GameRepository;
import com.gamersfamily.gamersfamily.repository.RatingRepository;
import com.gamersfamily.gamersfamily.service.GameService;
import com.gamersfamily.gamersfamily.utils.enums.Rate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameOriginalRatingDto {

    public static final String VALIDATION_MESSAGE_FOR_NAME = "Name of the game cannot be " +
            "empty and it should contain at least one non-whitespace character.";

    private Long id;

    @NotBlank(message = VALIDATION_MESSAGE_FOR_NAME)
    private String name;

    private String description;

    private String background_image;

    private Date released;

    private Set<CategoryFetchDto> categories;

    private Set<TagsDto> tags;

    private Set<PlatformDto> platforms;

    private Long Rating;

    public void setRating(List<Rate> ratings) {

        long sum = 0;
        for (Rate r: ratings) {
            sum = sum + r.getRate();
        }

        this.Rating = sum / ratings.size();
    }
}
