package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GameDto> getAllGames() {
        return gameRepository.findAll()
                .stream().map(game -> modelMapper.map(game,GameDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDto> getGamesByPage(Integer pageNumber, Integer pageSize) {
        return null;
    }
}
