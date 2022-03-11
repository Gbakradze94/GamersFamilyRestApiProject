package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.RatingDto;
import com.gamersfamily.gamersfamily.dto.RatingOutputDto;
import com.gamersfamily.gamersfamily.model.Rating;

import java.util.List;

public interface RatingService {
    RatingOutputDto saveRating(RatingDto rating);

    List<RatingOutputDto> getRatingsForNews(long newsId);

    RatingOutputDto deleteRating(long ratingId, long authorId);

    RatingOutputDto updateRating(RatingOutputDto rating);
}
