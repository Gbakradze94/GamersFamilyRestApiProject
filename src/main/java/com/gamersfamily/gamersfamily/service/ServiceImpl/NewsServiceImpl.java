package com.gamersfamily.gamersfamily.service.ServiceImpl;

import com.gamersfamily.gamersfamily.dto.NewsDto;
import com.gamersfamily.gamersfamily.exception.ResourceNotFoundException;
import com.gamersfamily.gamersfamily.mapper.NewsMapper;
import com.gamersfamily.gamersfamily.model.News;
import com.gamersfamily.gamersfamily.repository.NewsRepository;
import com.gamersfamily.gamersfamily.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
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
    public NewsDto createNews(NewsDto newsDto) {
        News newNews = newsRepository.save(newsMapper.dtoToEntity(newsDto));
        return newsMapper.entityToDto(newNews);
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


}
