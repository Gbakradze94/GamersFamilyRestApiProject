package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByNewsId(long id);
}
