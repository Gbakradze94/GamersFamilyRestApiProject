package com.gamersfamily.gamersfamily.mapper;

import com.gamersfamily.gamersfamily.dto.SubCommentDto;
import com.gamersfamily.gamersfamily.dto.SubCommentDtoOutput;
import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.SubComment;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.CommentRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SubCommentMapper {

    private ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final CommentRepository commentRepo;

    SubCommentMapper(ModelMapper modelMapper, UserRepository userRepo, CommentRepository commentRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
    }

    public SubComment dtoToEntity(SubCommentDto dto) {
        System.out.println("dto= "+dto);
        Converter<Long, User> toUserConverter = data -> userRepo.findById(data.getSource()).orElseThrow(() -> new IllegalArgumentException("user id not found "));
        Converter<Long, Comment> toCommentConverter = data -> commentRepo.findById(data.getSource()).orElseThrow(() -> new IllegalArgumentException("news id not found "));
        return modelMapper.typeMap(SubCommentDto.class, SubComment.class)
                .addMappings(mapper -> mapper.using(toUserConverter).map(SubCommentDto::getUserId, SubComment::setAuthor))
                .addMappings(mapper -> mapper.using(toCommentConverter).map(SubCommentDto::getCommentId, SubComment::setComment))
                .map(dto);

    }

    public SubCommentDtoOutput entityToDto(SubComment subComment) {
        Converter<User, Long> toUserIdConverter = user -> user.getSource().getId();
        Converter<Comment, Long> toCommentIdConverter = comment -> comment.getSource().getId();
        Converter<User, String> toUsername = user -> user.getSource().getUsername();
        return modelMapper.typeMap(SubComment.class, SubCommentDtoOutput.class)
                .addMappings(mapper -> mapper.using(toUserIdConverter).map(SubComment::getAuthor, SubCommentDtoOutput::setUserId))
                .addMappings(mapper -> mapper.using(toCommentIdConverter).map(SubComment::getComment, SubCommentDtoOutput::setCommentId))
                .addMappings(mapper -> mapper.using(toUsername).map(SubComment::getAuthor, SubCommentDtoOutput::setUsername))
                .map(subComment);
    }

}
