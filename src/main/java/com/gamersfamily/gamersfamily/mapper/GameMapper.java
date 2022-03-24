package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.dto.GameOriginalDto;
import com.gamersfamily.gamersfamily.dto.GameOriginalRatingDto;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.repository.RatingRepository;
import com.gamersfamily.gamersfamily.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    private final ModelMapper modelMapper;
    private final RatingRepository ratingRepository;

    public GameMapper(ModelMapper modelMapper, RatingRepository ratingRepository) {
        this.modelMapper = modelMapper;
        this.ratingRepository = ratingRepository;
    }

    public GameDto entityToDto(Game game){
        return modelMapper.map(game, GameDto.class);
    }

    public Game dtoToEntity(GameDto gameDto){
        return modelMapper.map(gameDto, Game.class);
    }

    public GameOriginalDto originalEntityToDto(Game game){
        return modelMapper.map(game, GameOriginalDto.class);
    }

    public Game originalDtoToEntity(GameOriginalDto gameDto){
        return modelMapper.map(gameDto, Game.class);
    }

    public GameOriginalRatingDto originalDtoToRatingEntity(Game game){
        GameOriginalRatingDto gameOriginalRatingDto = copyGameForRating(game);
        gameOriginalRatingDto.setRating(ratingRepository.getAllRatingsForGivenGame(game));
        return gameOriginalRatingDto;
    }

    private GameOriginalRatingDto copyGameForRating(Game game){
        return modelMapper.map(game, GameOriginalRatingDto.class);
    }
}
