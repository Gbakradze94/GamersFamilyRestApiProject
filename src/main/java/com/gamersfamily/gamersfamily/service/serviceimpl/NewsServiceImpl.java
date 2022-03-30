package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.dto.CommentOriginalDto;
import com.gamersfamily.gamersfamily.dto.CommentSimpleDto;
import com.gamersfamily.gamersfamily.dto.NewsCreateDto;
import com.gamersfamily.gamersfamily.dto.NewsDto;
import com.gamersfamily.gamersfamily.exception.BlogAPIException;
import com.gamersfamily.gamersfamily.exception.ResourceNotFoundException;
import com.gamersfamily.gamersfamily.mapper.CommentMapper;
import com.gamersfamily.gamersfamily.mapper.NewsMapper;
import com.gamersfamily.gamersfamily.model.*;
import com.gamersfamily.gamersfamily.repository.CommentRepository;
import com.gamersfamily.gamersfamily.repository.NewsRepository;
import com.gamersfamily.gamersfamily.repository.RatingRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import com.gamersfamily.gamersfamily.service.NewsService;
import com.gamersfamily.gamersfamily.utils.enums.Rate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public NewsServiceImpl(NewsRepository newsRepository
            , NewsMapper newsMapper
            , CommentMapper commentMapper
            , CommentRepository commentRepository
            , UserRepository userRepository, RatingRepository ratingRepository) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }


    @Override
    public List<NewsDto> getAllNews() {
        return newsRepository
                .findAll()
                .stream()
                .map(newsMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto getNewsById(long newsId) {
        News news = checkNewsById(newsId);
        return newsMapper.entityToDto(news);
    }

    @Override
    public List<CommentOriginalDto> getAllCommentsForNews(long newsId) {
        return commentRepository.findByNewsId(newsId)
                .stream()
                .map(commentMapper::entityToOriginalDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsCreateDto createNews(NewsCreateDto newsDto) {
        News newNews = newsRepository.save(newsMapper.createNewsDtoToEntity(newsDto));
        return newsMapper.entityToCreateNewsDto(newNews);
    }

    @Override
    public NewsDto updateNews(long newsId, NewsDto newsDto) {
        News news = checkNewsById(newsId);
        news.setName(newsDto.getName());
        news.setBody(newsDto.getBody());
        news.setComments(newsDto.getComments());
        news.setRatings(newsDto.getRatings());
        News updatedNews = newsRepository.save(news);
        return newsMapper.entityToDto(updatedNews);
    }

    @Override
    public void deleteNews(long newsId) {
        News news = checkNewsById(newsId);
        newsRepository.delete(news);
    }

    @Override
    public News checkNewsById(long newsId) {
        return newsRepository.findById(newsId).orElseThrow(
                ()-> new ResourceNotFoundException("News", "id", newsId));
    }

    @Override
    public void addCommentToNews(CommentSimpleDto simpleDto, long newsId) {
        News news = checkNewsById(newsId);
        User user = userRepository.findByEmail(getAuthenticatedUserEmail()).orElseThrow(
                () -> new UsernameNotFoundException(getAuthenticatedUserEmail()));
        if(!user.isEnabled()){
            throw new BlogAPIException("Please Verify Email To Add Comment", HttpStatus.BAD_REQUEST);
        }
        CommentOriginalDto commentOriginalDto = new CommentOriginalDto();
        commentOriginalDto.setBody(simpleDto.getBody());
        commentOriginalDto.setAuthor(user);
        commentOriginalDto.setNews(news);
        Comment savedComment = commentRepository.save(commentMapper.originalDtoToEntity(commentOriginalDto));
        news.getComments().add(savedComment);
    }

    @Override
    public News addRatingToNews(Long newsId, Rate rate) {
        User user = userRepository.findByEmail(getAuthenticatedUserEmail()).orElseThrow(
                NoSuchElementException::new
        );

        News news = checkNewsById(newsId);
        if(ratingRepository.countUserRatingForGivenNews(news, user) >= 1){
            throw new BlogAPIException(("User: " + user.getUsername() +
                    " Already Gave A rating to The News by ID: " + newsId), HttpStatus.BAD_REQUEST);
        }
        Rating rating = new Rating();
        rating.setAuthor(user);
        rating.setRate(rate);
        rating.setNews(news);
        ratingRepository.save(rating);
        news.getRatings().add(rating);
        return news;
    }

    private String getAuthenticatedUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
