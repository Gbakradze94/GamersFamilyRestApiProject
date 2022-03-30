package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.model.Rating;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.utils.enums.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByNewsId(long id);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.game=?1 AND r.author=?2")
    long countUserRatingForGivenGame(Game game, User user);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.news=?1 AND r.author=?2")
    long countUserRatingForGivenNews(News news, User user);

    @Query("SELECT r.rate FROM Rating r WHERE r.game=?1")
    List<Rate> getAllRatingsForGivenGame(Game game);

    @Query("SELECT r.rate FROM Rating r WHERE r.news=?1")
    List<Rate> getAllRatingsForGivenNews(News news);
}
