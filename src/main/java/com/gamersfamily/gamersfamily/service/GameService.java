package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.dto.GameOriginalDto;
import com.gamersfamily.gamersfamily.dto.GameOriginalRatingDto;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.utils.enums.Rate;

import java.util.List;
import java.util.Optional;


public interface GameService {

    List<GameOriginalRatingDto> getAllGames();

    List<GameOriginalRatingDto> getGamesByPage(Integer pageNumber, Integer pageSize);

    List<GameOriginalRatingDto> getAllGamesByPlatform(String platform);

    List<GameOriginalRatingDto> getAllGamesByTag(String tag);

    List<GameOriginalRatingDto> getAllGamesByCategory(String category);

    Game saveGame(GameDto gameDto);

    void deleteGame(Long id);

    Game updateGame(GameOriginalDto gameDto);

    Optional<Game> findByName(String name);

    Game addRatingToGame(long gameId, Rate rate);

    Game checkGameById(long gameId);
}
