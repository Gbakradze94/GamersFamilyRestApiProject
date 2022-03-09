package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.NewsRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class CommentMapper {

    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final NewsRepository newsRepo;

    public CommentMapper(ModelMapper modelMapper, UserRepository userRepo, NewsRepository newsRepo) {

        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.newsRepo = newsRepo;
    }

    public CommentDto entityToDto(Comment comment) {
        Converter<User, Long> toUserIdConverter = user -> user.getSource().getId();
        Converter<News, Long> toNewsIdConverter = news -> news.getSource().getId();

        return modelMapper.typeMap(Comment.class, CommentDto.class)
                .addMappings(mapper -> mapper.using(toUserIdConverter).map(Comment::getAuthor, CommentDto::setUserId))
                .addMappings(mapper -> mapper.using(toNewsIdConverter).map(Comment::getNews, CommentDto::setNewsId))
                .map(comment);
    }

    public Comment dtoToEntity(CommentDto dto) {
        Converter<Long, User> toUserConverter = data -> userRepo.findById(data.getSource()).orElseThrow(() -> new IllegalArgumentException("user id not found "));
        Converter<Long, News> toNewsConverter = data -> newsRepo.findById(data.getSource()).orElseThrow(() -> new IllegalArgumentException("news id not found "));
        return modelMapper.typeMap(CommentDto.class, Comment.class)
                .addMappings(mapper -> mapper.skip(CommentDto::getId, Comment::setId))
                .addMappings(mapper -> mapper.using(toUserConverter).map(CommentDto::getUserId, Comment::setAuthor))
                .addMappings(mapper -> mapper.using(toNewsConverter).map(CommentDto::getNewsId, Comment::setNews))
                .map(dto);

    }
}
