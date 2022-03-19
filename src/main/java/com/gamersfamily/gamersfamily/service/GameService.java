package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.model.Game;

import java.util.List;
import java.util.Optional;


public interface GameService {

    List<GameDto> getAllGames();

    List<GameDto> getGamesByPage(Integer pageNumber, Integer pageSize);

    Game saveGame(GameDto gameDto);

    void deleteGame(Long id);

    Game updateGame(GameDto gameDto);

    Optional<Game> findByName(String name);
}
