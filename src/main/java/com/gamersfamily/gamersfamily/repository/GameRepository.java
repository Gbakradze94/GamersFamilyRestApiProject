package com.gamersfamily.gamersfamily.repository;


import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface GameRepository extends PagingAndSortingRepository<Game,Long> {
    List<Game> findAll();
    Optional<Game> findByName(String name);
}
