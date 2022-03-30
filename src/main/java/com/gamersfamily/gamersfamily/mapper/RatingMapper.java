package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.RatingDto;
import com.gamersfamily.gamersfamily.dto.RatingOutputDto;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.model.Rating;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.NewsRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {
    private ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final NewsRepository newsRepo;

    public RatingMapper(ModelMapper modelMapper, UserRepository userRepo, NewsRepository newsRepo) {
        this.modelMapper = modelMapper;
        this.newsRepo = newsRepo;
        this.userRepo = userRepo;
    }

    public RatingOutputDto entityToDto(Rating rating) {
        Converter<User, Long> toUserIdConverter = user -> user.getSource().getId();
        Converter<News, Long> toNewsIdConverter = news -> news.getSource().getId();
        Converter<User, String> toUsername = user -> user.getSource().getUsername();
        return modelMapper.typeMap(Rating.class, RatingOutputDto.class)
                .addMappings(mapper -> mapper.using(toUserIdConverter).map(Rating::getAuthor, RatingOutputDto::setUser))
                .addMappings(mapper -> mapper.using(toNewsIdConverter).map(Rating::getNews, RatingOutputDto::setNews))
                .addMappings(mapper -> mapper.using(toUsername).map(Rating::getAuthor, RatingOutputDto::setUsername))
                .map(rating);
    }

    public Rating dtoToEntity(RatingDto dto) {
        Converter<Long, User> toUserConverter = data -> userRepo.findById(data.getSource()).orElseThrow(() -> new IllegalArgumentException("user id not found "));
        Converter<Long, News> toNewsConverter = data -> newsRepo.findById(data.getSource()).orElseThrow(() -> new IllegalArgumentException("news id not found "));
        return modelMapper.typeMap(RatingDto.class, Rating.class)
                .addMappings(mapper -> mapper.using(toUserConverter).map(RatingDto::getUser, Rating::setAuthor))
                .addMappings(mapper -> mapper.using(toNewsConverter).map(RatingDto::getNews, Rating::setNews))
                .map(dto);

    }
}
