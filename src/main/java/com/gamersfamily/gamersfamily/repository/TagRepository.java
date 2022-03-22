package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
