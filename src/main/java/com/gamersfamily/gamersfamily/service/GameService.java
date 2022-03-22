package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.dto.GameOriginalDto;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.utils.enums.Rate;

import java.util.List;
import java.util.Optional;


public interface GameService {

    List<GameOriginalDto> getAllGames();

    List<GameOriginalDto> getGamesByPage(Integer pageNumber, Integer pageSize);

    Game saveGame(GameDto gameDto);

    void deleteGame(Long id);

    Game updateGame(GameOriginalDto gameDto);

    Optional<Game> findByName(String name);

    Game addRatingToGame(long gameId, Rate rate);

    Game checkGameById(long gameId);
}
