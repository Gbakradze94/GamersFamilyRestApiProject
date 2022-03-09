package com.gamersfamily.gamersfamily.repository;


import com.gamersfamily.gamersfamily.model.Game;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface GameRepository extends PagingAndSortingRepository<Game,Long> {
    List<Game> findAll();
}
