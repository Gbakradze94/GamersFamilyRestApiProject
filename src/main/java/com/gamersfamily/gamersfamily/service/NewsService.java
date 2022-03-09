package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.NewsDto;
import com.gamersfamily.gamersfamily.model.News;

import java.util.List;

public interface NewsService {
    List<NewsDto> getAllNews();
    NewsDto getNewsById(long newsId);
    NewsDto createNews(NewsDto newsDto);
    NewsDto updateNews(long newsId, NewsDto newsDto);
    void deleteNews(long newsId);
    News checkNewsById(long newsId);
}
