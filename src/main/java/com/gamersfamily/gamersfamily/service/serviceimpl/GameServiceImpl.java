package com.gamersfamily.gamersfamily.service.serviceimpl;


import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.repository.GameRepository;
import com.gamersfamily.gamersfamily.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper){
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
        Pageable pages = PageRequest.of(pageNumber,pageSize);
        return gameRepository.findAll(pages)
                .getContent()
                .stream()
                .map(game -> modelMapper.map(game,GameDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Game saveGame(GameDto gameDto) {
        return gameRepository.save(modelMapper.map(gameDto,Game.class));
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game updateGame(GameDto gameDto) {
        return gameRepository.save(modelMapper.map(gameDto,Game.class));
    }

}