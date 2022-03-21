package com.gamersfamily.gamersfamily.service.serviceimpl;


import com.gamersfamily.gamersfamily.dto.CategoryFetchDto;
import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.dto.TagsDto;
import com.gamersfamily.gamersfamily.mapper.CategoryMapper;
import com.gamersfamily.gamersfamily.mapper.GameMapper;
import com.gamersfamily.gamersfamily.mapper.TagMapper;
import com.gamersfamily.gamersfamily.model.Category;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.model.Tag;
import com.gamersfamily.gamersfamily.repository.CategoryRepository;
import com.gamersfamily.gamersfamily.repository.GameRepository;
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

    private final GameMapper gameMapper;

    public GameServiceImpl(GameRepository gameRepository, CategoryRepository categoryRepository, CategoryMapper categoryMapper, TagRepository tagRepository, TagMapper tagMapper, GameMapper gameMapper){
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
        this.gameMapper = gameMapper;
    }

    @Override
    public List<GameDto> getAllGames() {
        return gameRepository.findAll()
                .stream().map(gameMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GameDto> getGamesByPage(Integer pageNumber, Integer pageSize) {
        Pageable pages = PageRequest.of(pageNumber,pageSize);
        return gameRepository.findAll(pages)
                .getContent()
                .stream()
                .map(gameMapper::entityToDto)
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
    public Game updateGame(GameDto gameDto) {
        return gameRepository.save(gameMapper.dtoToEntity(gameDto));
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

    private Game setGameProperties(GameDto gameDto){
        Set<Category> categorySet = checkCategory(gameDto);
        Set<Tag> tagSet = checkTag(gameDto);
        Game game = gameMapper.dtoToEntity(gameDto);
        game.setCategories(categorySet);
        game.setTags(tagSet);
        return game;
    }

}