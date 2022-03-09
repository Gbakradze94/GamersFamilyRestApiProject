package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class CommentMapper {

    private final ModelMapper modelMapper;


    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CommentDto entityToDto(Comment comment) {
       // Converter<Long, User> toUserConverter=data->

        modelMapper.typeMap(Comment.class,CommentDto.class)
                .addMappings(mapper->mapper.using())
                .map(comment);
        return null;
    }

    public Comment dtoToEntity(CommentDto dto) {
        return null;
    }
}
