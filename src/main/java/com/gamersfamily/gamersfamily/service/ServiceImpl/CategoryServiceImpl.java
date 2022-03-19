package com.gamersfamily.gamersfamily.service.ServiceImpl;

import com.gamersfamily.gamersfamily.dto.CategoryDto;
import com.gamersfamily.gamersfamily.mapper.CategoryMapper;
import com.gamersfamily.gamersfamily.repository.CategoryRepository;
import com.gamersfamily.gamersfamily.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
