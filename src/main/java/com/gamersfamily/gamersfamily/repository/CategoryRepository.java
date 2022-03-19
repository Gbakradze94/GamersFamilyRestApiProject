package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Category;
import com.gamersfamily.gamersfamily.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
