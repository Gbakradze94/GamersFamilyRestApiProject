package com.gamersfamily.gamersfamily.controller;

import com.gamersfamily.gamersfamily.dto.CommentOriginalDto;
import com.gamersfamily.gamersfamily.dto.CommentSimpleDto;
import com.gamersfamily.gamersfamily.dto.NewsCreateDto;
import com.gamersfamily.gamersfamily.dto.NewsDto;
import com.gamersfamily.gamersfamily.service.NewsService;
import com.gamersfamily.gamersfamily.utils.enums.Rate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(NewsController.BASE_URL)
public class NewsController {

    public static final String BASE_URL = "/api/v1/news";

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

    @GetMapping("newsComments/{newsId}")
    public ResponseEntity<List<CommentOriginalDto>> getAllCommentsForNews(@PathVariable("newsId") long newsId){
        logger.info("Getting All Comments for News By id: " + newsId);
        List<CommentOriginalDto> mylist = newsService.getAllCommentsForNews(newsId);
        System.out.println(mylist.toString());
        return new ResponseEntity<>(newsService.getAllCommentsForNews(newsId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping()
    public ResponseEntity<NewsCreateDto> createNews(@Valid @RequestBody NewsCreateDto newsDto){
        logger.info("Creating News");
        return new ResponseEntity<>(newsService.createNews(newsDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PutMapping("/{newsId}")
    public ResponseEntity<NewsDto> updateNews(
            @PathVariable(value = "newsId") Long newsId,
            @Valid @RequestBody NewsDto newsDto){
        logger.info("Updating News By ID: " + newsDto);
        NewsDto updatedNews = newsService.updateNews(newsId, newsDto);
        return new ResponseEntity<>(updatedNews, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @DeleteMapping("/{newsId}")
    public ResponseEntity<String> deleteNews(
            @PathVariable(value = "newsId") Long newsId
    ){
        logger.info("Deleting news By ID: " + newsId);
        newsService.deleteNews(newsId);
        return new ResponseEntity<>("News By Id: " + newsId +" Deleted Successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
    @PatchMapping("comments/{newsId}")
    public ResponseEntity<String> addCommentToNews(
            @PathVariable(value = "newsId") Long newsId,
            @Valid @RequestBody CommentSimpleDto commentSimpleDto
    ){
        logger.info("Adding Comment To news By ID: " + newsId);
        newsService.addCommentToNews(commentSimpleDto, newsId);
        return new ResponseEntity<>("Comment Added to news by id:  " + newsId, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
    @PatchMapping("ratings/{newsId}")
    public ResponseEntity<HttpStatus> addRatingToNews(
            @PathVariable(value = "newsId") Long newsId,
            Rate rate
    ){
        newsService.addRatingToNews(newsId, rate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
