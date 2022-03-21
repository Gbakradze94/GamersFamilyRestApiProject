package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.CategoryDto;
import com.gamersfamily.gamersfamily.dto.CategoryFetchDto;
import com.gamersfamily.gamersfamily.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDto entityToDto(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category dtoToEntity(CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }

    public CategoryFetchDto entityToFetchDto(Category category){
        return modelMapper.map(category, CategoryFetchDto.class);
    }

    public Category fetchDtoToEntity(CategoryFetchDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }
}
