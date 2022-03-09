package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.mapper.CommentMapper;
import com.gamersfamily.gamersfamily.repository.CommentRepository;
import com.gamersfamily.gamersfamily.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepo;

    CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepo) {
        this.commentMapper = commentMapper;
        this.commentRepo = commentRepo;

    }

    @Override
    public CommentDto saveComment(CommentDto comment) {
        return commentMapper.entityToDto(commentRepo.save(commentMapper.dtoToEntity(comment)));
    }

    @Override
    public List<CommentDto> getComments(long newsId) {
        return null;
    }

    @Override
    public CommentDto editComment(CommentDto comment) {
        return null;
    }

    @Override
    public CommentDto deleteComment(long commentId) {
        return null;
    }
}
