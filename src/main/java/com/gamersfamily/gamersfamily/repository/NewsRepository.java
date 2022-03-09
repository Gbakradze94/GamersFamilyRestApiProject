package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findById(long id);
}
