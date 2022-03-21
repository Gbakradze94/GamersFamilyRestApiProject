package com.gamersfamily.gamersfamily.service.serviceimpl;


import com.gamersfamily.gamersfamily.dto.CategoryFetchDto;
import com.gamersfamily.gamersfamily.dto.GameDto;
import com.gamersfamily.gamersfamily.mapper.CategoryMapper;
import com.gamersfamily.gamersfamily.mapper.GameMapper;
import com.gamersfamily.gamersfamily.model.Category;
import com.gamersfamily.gamersfamily.model.Game;
import com.gamersfamily.gamersfamily.repository.CategoryRepository;
import com.gamersfamily.gamersfamily.repository.GameRepository;
import com.gamersfamily.gamersfamily.service.GameService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private final GameMapper gameMapper;

    public GameServiceImpl(GameRepository gameRepository, CategoryRepository categoryRepository, CategoryMapper categoryMapper, GameMapper gameMapper){
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
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
        checkCategory(gameDto);
        return gameRepository.save(gameMapper.dtoToEntity(gameDto));
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

    private void checkCategory(GameDto gameDto){
        if(gameDto.getCategories().isEmpty()){
            return;
        }
//        [adventure, puzzle, action]
        Set<CategoryFetchDto> categorySet = new HashSet<>();
        for (CategoryFetchDto category:gameDto.getCategories()) {
            Optional<Category> dbCategory = categoryRepository.findByName(category.getName());
            if(dbCategory.isEmpty()){
                categoryRepository.save(categoryMapper.fetchDtoToEntity(category));
                categorySet.add(category);
            }else{
                categorySet.add(categoryMapper.entityToFetchDto(dbCategory.get()));
            }
        }
        gameDto.setCategories(categorySet);
    }

}