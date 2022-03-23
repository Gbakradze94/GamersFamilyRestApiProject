package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Game;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;
import java.util.Optional;


public interface GameRepository extends PagingAndSortingRepository<Game,Long> {
    List<Game> findAll();
    List<Game> findByPlatforms_NameIgnoreCase(String platformName);
    List<Game> findByTags_NameIgnoreCase(String tagName);
    List<Game> findByCategories_NameIgnoreCase(String tagName);
    Optional<Game> findByName(String name);

}
