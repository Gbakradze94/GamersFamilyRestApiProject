package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.NewsCreateDto;
import com.gamersfamily.gamersfamily.dto.NewsDto;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.repository.RatingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    private final ModelMapper modelMapper;
    private final RatingRepository ratingRepository;

    public NewsMapper(ModelMapper modelMapper, RatingRepository ratingRepository) {
        this.modelMapper = modelMapper;
        this.ratingRepository = ratingRepository;
    }

    public News dtoToEntity(NewsDto newsDto){
        return modelMapper.map(newsDto, News.class);
    }

    public NewsCreateDto entityToCreateNewsDto(News news){
        return modelMapper.map(news, NewsCreateDto.class);
    }

    public News createNewsDtoToEntity(NewsCreateDto newsDto){
        return modelMapper.map(newsDto, News.class);
    }

    public NewsDto entityToDto(News news){
        NewsDto newsDto = copyNewsForRating(news);
        newsDto.setRating(ratingRepository.getAllRatingsForGivenNews(news));
        return newsDto;
    }

    private NewsDto copyNewsForRating(News news){
        return modelMapper.map(news, NewsDto.class);
    }
}
