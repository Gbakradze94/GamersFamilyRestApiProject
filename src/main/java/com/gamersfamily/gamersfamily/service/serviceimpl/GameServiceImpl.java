package com.gamersfamily.gamersfamily.service.serviceimpl;


import com.gamersfamily.gamersfamily.dto.*;
import com.gamersfamily.gamersfamily.mapper.CategoryMapper;
import com.gamersfamily.gamersfamily.mapper.GameMapper;
import com.gamersfamily.gamersfamily.mapper.TagMapper;
import com.gamersfamily.gamersfamily.model.Category;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.model.Platform;
import com.gamersfamily.gamersfamily.model.Tag;
import com.gamersfamily.gamersfamily.repository.CategoryRepository;
import com.gamersfamily.gamersfamily.repository.GameRepository;
import com.gamersfamily.gamersfamily.repository.PlatformRepository;
import com.gamersfamily.gamersfamily.repository.TagRepository;
import com.gamersfamily.gamersfamily.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    private final GameMapper gameMapper;

    public GameServiceImpl(GameRepository gameRepository,
                           CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper,
                           TagRepository tagRepository,
                           TagMapper tagMapper,
                           PlatformRepository platformRepository,
                           GameMapper gameMapper){
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
        this.platformRepository = platformRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public List<GameOriginalDto> getAllGames() {
        return gameRepository.findAll()
                .stream().map(gameMapper::originalEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameOriginalDto> getGamesByPage(Integer pageNumber, Integer pageSize) {
        Pageable pages = PageRequest.of(pageNumber,pageSize);
        return gameRepository.findAll(pages)
                .getContent()
                .stream()
                .map(gameMapper::originalEntityToDto)
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