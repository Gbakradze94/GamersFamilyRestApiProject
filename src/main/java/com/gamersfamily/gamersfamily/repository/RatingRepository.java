package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<SubComment, Long> {
}
