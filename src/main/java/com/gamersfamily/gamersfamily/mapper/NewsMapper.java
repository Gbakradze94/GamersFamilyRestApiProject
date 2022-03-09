package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.NewsDto;
import com.gamersfamily.gamersfamily.model.News;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    private final ModelMapper modelMapper;

    public NewsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NewsDto entityToDto(News news){
        return modelMapper.map(news, NewsDto.class);
    }

    public News dtoToEntity(NewsDto newsDto){
        return modelMapper.map(newsDto, News.class);
    }
}
