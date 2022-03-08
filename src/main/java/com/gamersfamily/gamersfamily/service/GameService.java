package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.GameDto;

import java.util.List;

public interface GameService {

    List<GameDto> getAllGames();

    List<GameDto> getGamesByPage(Integer pageNumber, Integer pageSize);
}
