package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.dto.NewsDto;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.model.News;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    private final ModelMapper modelMapper;

    public GameMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GameDto entityToDto(Game game){
        return modelMapper.map(game, GameDto.class);
    }

    public Game dtoToEntity(GameDto gameDto){
        return modelMapper.map(gameDto, Game.class);
    }
}
