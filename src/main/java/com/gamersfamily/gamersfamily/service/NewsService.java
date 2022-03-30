package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.*;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.utils.enums.Rate;

import java.util.List;

public interface NewsService {
    List<NewsDto> getAllNews();
    NewsDto getNewsById(long newsId);
    List<CommentOriginalDto> getAllCommentsForNews(long newsId);
    NewsCreateDto createNews(NewsCreateDto newsDto);
    NewsDto updateNews(long newsId, NewsDto newsDto);
    void deleteNews(long newsId);
    News checkNewsById(long newsId);
    void addCommentToNews(CommentSimpleDto comment, long newsId);

    News addRatingToNews(Long newsId, Rate rate);
}
