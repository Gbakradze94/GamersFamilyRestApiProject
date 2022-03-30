package com.gamersfamily.gamersfamily.service.serviceimpl;


import com.gamersfamily.gamersfamily.dto.*;
import com.gamersfamily.gamersfamily.exception.BlogAPIException;
import com.gamersfamily.gamersfamily.exception.ResourceNotFoundException;
import com.gamersfamily.gamersfamily.mapper.CategoryMapper;
import com.gamersfamily.gamersfamily.mapper.GameMapper;
import com.gamersfamily.gamersfamily.mapper.TagMapper;
import com.gamersfamily.gamersfamily.model.*;
import com.gamersfamily.gamersfamily.repository.*;
import com.gamersfamily.gamersfamily.service.GameService;
import com.gamersfamily.gamersfamily.utils.enums.Rate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final PlatformRepository platformRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    private final GameMapper gameMapper;

    public GameServiceImpl(GameRepository gameRepository,
                           CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper,
                           TagRepository tagRepository,
                           TagMapper tagMapper,
                           PlatformRepository platformRepository,
                           UserRepository userRepository, RatingRepository ratingRepository, GameMapper gameMapper){
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
        this.platformRepository = platformRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public List<GameOriginalRatingDto> getAllGames() {
        return gameRepository.findAll()
                .stream().map(gameMapper::originalDtoToRatingEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameOriginalRatingDto> getGamesByPage(Integer pageNumber, Integer pageSize) {
        Pageable pages = PageRequest.of(pageNumber,pageSize);
        return gameRepository.findAll(pages)
                .getContent()
                .stream()
                .map(gameMapper::originalDtoToRatingEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameOriginalRatingDto> getAllGamesByPlatform(String platform) {
        return gameRepository.findByPlatforms_NameIgnoreCase(platform)
                .stream().map(gameMapper::originalDtoToRatingEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameOriginalRatingDto> getAllGamesByTag(String tag) {
        return gameRepository.findByTags_NameIgnoreCase(tag)
                .stream().map(gameMapper::originalDtoToRatingEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameOriginalRatingDto> getAllGamesByCategory(String category) {
        return gameRepository.findByCategories_NameIgnoreCase(category)
                .stream().map(gameMapper::originalDtoToRatingEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Game saveGame(GameDto gameDto) {
        Game game = setGameProperties(gameDto);
        return gameRepository.save(game);
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game updateGame(GameOriginalDto gameDto) {
        return gameRepository.save(gameMapper.originalDtoToEntity(gameDto));
    }

    @Override
    public Optional<Game> findByName(String name) {
        return gameRepository.findByName(name);
    }

    @Override
    public Game addRatingToGame(long gameId, Rate rate) {
        User user = userRepository.findByEmail(getAuthenticatedUserEmail()).orElseThrow(
                NoSuchElementException::new
        );

        Game game = checkGameById(gameId);
        if(ratingRepository.countUserRatingForGivenGame(game, user) >= 1){
            throw new BlogAPIException(("User: " + user.getUsername() +
                    " Already Gave A rating to The Game by ID: " + gameId), HttpStatus.BAD_REQUEST);
        }
        Rating rating = new Rating();
        rating.setAuthor(user);
        rating.setRate(rate);
        rating.setGame(game);
        ratingRepository.save(rating);
        game.getRatings().add(rating);
        return game;
    }

    @Override
    public Game checkGameById(long gameId) {
        return gameRepository.findById(gameId).orElseThrow(
                ()-> new ResourceNotFoundException("Game", "id", gameId));
    }

    private String getAuthenticatedUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private Set<Category> checkCategory(GameDto gameDto){

        if(gameDto.getCategories().isEmpty()){
            return new HashSet<>();
        }

        Set<Category> categorySet = new HashSet<>();
        for (CategoryFetchDto category:gameDto.getCategories()) {
            Optional<Category> dbCategory = categoryRepository.findByName(category.getName());
            if(dbCategory.isEmpty()){
                Category savedCategory = categoryRepository.save(categoryMapper.fetchDtoToEntity(category));
                categorySet.add(savedCategory);
            }else{
                categorySet.add(dbCategory.get());
            }

        }
        return categorySet;
    }

    private Set<Tag> checkTag(GameDto gameDto){
        if(gameDto.getTags().isEmpty()){
            return new HashSet<>();
        }

        Set<Tag> tagSet = new HashSet<>();
        for (TagsDto tag:gameDto.getTags()) {
            Optional<Tag> dbCategory = tagRepository.findByName(tag.getName());
            if(dbCategory.isEmpty()){
                Tag savedTag = tagRepository.save(tagMapper.dtoToEntity(tag));
                tagSet.add(savedTag);
            }else{
                tagSet.add(dbCategory.get());
            }

        }
        return tagSet;

    }

    private Set<Platform> checkPlatform(GameDto gameDto){
        if(gameDto.getPlatforms().isEmpty()){
            return new HashSet<>();
        }

        Set<Platform> platformSet = new HashSet<>();
        for (PlatformsDto platform:gameDto.getPlatforms()) {
            Optional<Platform> dbCategory = platformRepository.findByName(platform.getPlatform().getName());
            if(dbCategory.isEmpty()){
                Platform newPlatform = new Platform();
                newPlatform.setName(platform.getPlatform().getName());
                Platform savedPlatform = platformRepository.save(newPlatform);
                platformSet.add(savedPlatform);
            }else{
                platformSet.add(dbCategory.get());
            }

        }
        return platformSet;
    }

    private Game setGameProperties(GameDto gameDto){
        Set<Category> categorySet = checkCategory(gameDto);
        Set<Tag> tagSet = checkTag(gameDto);
        Set<Platform> platformSet = checkPlatform(gameDto);
        Game game = gameMapper.dtoToEntity(gameDto);
        game.setCategories(categorySet);
        game.setTags(tagSet);
        game.setPlatforms(platformSet);
        return game;
    }

}