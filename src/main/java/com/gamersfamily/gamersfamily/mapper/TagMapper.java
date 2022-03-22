package com.gamersfamily.gamersfamily.mapper;


import com.gamersfamily.gamersfamily.dto.TagsDto;
import com.gamersfamily.gamersfamily.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    private final ModelMapper modelMapper;

    public TagMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TagsDto entityToDto(Tag tag){
        return modelMapper.map(tag, TagsDto.class);
    }

    public Tag dtoToEntity(TagsDto tagsDto){
        return modelMapper.map(tagsDto, Tag.class);
    }

}
