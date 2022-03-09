package com.gamersfamily.gamersfamily.controller;

import com.gamersfamily.gamersfamily.dto.NewsDto;
import com.gamersfamily.gamersfamily.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews(){
        logger.info("Getting All News");
        return new ResponseEntity<>(newsService.getAllNews(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable("id") long newsId){
        logger.info(String.format("Getting news by ID: %d", newsId));
        return ResponseEntity.ok(newsService.getNewsById(newsId));
    }

    @PostMapping()
    public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto){
        logger.info("Creating News");
        return new ResponseEntity<>(newsService.createNews(newsDto), HttpStatus.CREATED);
    }

    @PutMapping("/{newsId}")
    public ResponseEntity<NewsDto> updateNews(
            @PathVariable(value = "newsId") Long newsId,
            @Valid @RequestBody NewsDto newsDto){
        logger.info("Updating News By ID: " + newsDto);
        NewsDto updatedNews = newsService.updateNews(newsId, newsDto);
        return new ResponseEntity<>(updatedNews, HttpStatus.OK);
    }

    @DeleteMapping("/{newsId}")
    public ResponseEntity<String> deleteNews(
            @PathVariable(value = "newsId") Long newsId
    ){
        logger.info("Deleting news By ID: " + newsId);
        newsService.deleteNews(newsId);
        return new ResponseEntity<>("News By Id: " + newsId +" Deleted Successfully", HttpStatus.OK);
    }
}