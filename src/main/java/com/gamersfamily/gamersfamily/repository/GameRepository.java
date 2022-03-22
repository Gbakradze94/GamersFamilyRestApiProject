package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Game;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;
import java.util.Optional;


public interface GameRepository extends PagingAndSortingRepository<Game,Long> {
    List<Game> findAll();
    Optional<Game> findByName(String name);
}
