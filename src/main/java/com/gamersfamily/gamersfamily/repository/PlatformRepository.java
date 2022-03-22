package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Optional<Platform> findByName(String name);
}
