package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.model.Rating;
import com.gamersfamily.gamersfamily.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByNewsId(long id);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.game=?1 AND r.author=?2")
    long countUserRatingForGivenGame(Game game, User user);
}
