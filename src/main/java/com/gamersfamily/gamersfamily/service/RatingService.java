package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.RatingDto;
import com.gamersfamily.gamersfamily.model.Rating;

public interface RatingService {
    Rating saveRating(RatingDto rating);
    Rating getRatingsForNews(long newsId);
    Rating deleteRating(long ratingId);
    Rating updateRating(RatingDto rating);
}
