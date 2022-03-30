package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.dto.CommentDtoOutput;
import com.gamersfamily.gamersfamily.dto.CommentOriginalDto;
import com.gamersfamily.gamersfamily.dto.NewsDto;
import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.NewsRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final NewsRepository newsRepo;

    public CommentMapper(ModelMapper modelMapper, UserRepository userRepo, NewsRepository newsRepo) {

        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.newsRepo = newsRepo;
    }

    public CommentDtoOutput entityToDto(Comment comment) {
        System.out.println("comment that will be converted"+comment);
        Converter<User, Long> toUserIdConverter = user -> user.getSource().getId();
        Converter<News, Long> toNewsIdConverter = news -> news.getSource().getId();
        Converter<User,String> toUsername=user->user.getSource().getUsername();
        return modelMapper.typeMap(Comment.class, CommentDtoOutput.class)
                .addMappings(mapper -> mapper.using(toUserIdConverter).map(Comment::getAuthor, CommentDtoOutput::setUserId))
                .addMappings(mapper -> mapper.using(toNewsIdConverter).map(Comment::getNews, CommentDtoOutput::setNewsId))
                .addMappings(mapper->mapper.using(toUsername).map(Comment::getAuthor,CommentDtoOutput::setUsername))
                .map(comment);
    }


    public Comment dtoToEntity(CommentDto dto) {

        Converter<Long, User> toUserConverter = data -> userRepo.findById(data.getSource()).orElseThrow(() -> new IllegalArgumentException("user id not found "));
        Converter<Long, News> toNewsConverter = data -> newsRepo.findById(data.getSource()).orElseThrow(() -> new IllegalArgumentException("news id not found "));
        return modelMapper.typeMap(CommentDto.class, Comment.class)
                .addMappings(mapper -> mapper.using(toUserConverter).map(CommentDto::getUserId, Comment::setAuthor))
                .addMappings(mapper -> mapper.using(toNewsConverter).map(CommentDto::getNewsId, Comment::setNews))
                .map(dto);

    }

    public CommentOriginalDto entityToOriginalDto(Comment comment){
        return modelMapper.map(comment, CommentOriginalDto.class);
    }

    public Comment originalDtoToEntity(CommentOriginalDto commentOriginalDto){
        return modelMapper.map(commentOriginalDto, Comment.class);
    }
}
