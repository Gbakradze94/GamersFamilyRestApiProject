package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
