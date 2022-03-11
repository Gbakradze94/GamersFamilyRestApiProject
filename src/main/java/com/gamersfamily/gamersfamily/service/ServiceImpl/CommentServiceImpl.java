package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.dto.CommentDtoOutput;
import com.gamersfamily.gamersfamily.mapper.CommentMapper;
import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.repository.CommentRepository;
import com.gamersfamily.gamersfamily.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepo;

    CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepo) {
        this.commentMapper = commentMapper;
        this.commentRepo = commentRepo;

    }

    @Override
    public CommentDtoOutput saveComment(CommentDto comment) {
        return commentMapper.entityToDto(commentRepo.save(commentMapper.dtoToEntity(comment)));
    }

    @Override
    public List<CommentDtoOutput> getComments(long newsId) {
        return commentRepo.findByNewsId(newsId)
                .stream()
                .map(commentMapper::entityToDto).toList();

    }

    @Override
    public CommentDtoOutput editComment(CommentDtoOutput comment) {
        //check if comment belongs to the user
        Comment commentFound = commentRepo.findById(comment.getId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("comment with this id can not be found to change");
                });
        commentFound.setBody(comment.getBody());
        commentFound.setUpdated(comment.getUpdated());
        if (commentFound.getAuthor().getId() == comment.getUserId() && commentFound.getNews().getId() == comment.getNewsId())
            return commentMapper.entityToDto(commentRepo.save(commentFound));
        else throw new IllegalArgumentException("newsId or UserId is not correct");
    }

    @Override
    public CommentDtoOutput deleteComment(long commentId, long authorId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> {
            throw new IllegalArgumentException("comment with this id can not be found to delete");
        });
        if (comment.getAuthor().getId() == authorId) {
            CommentDtoOutput output = commentMapper.entityToDto(comment);
            commentRepo.delete(comment);
            return output;
        } else {
            throw new IllegalArgumentException("authorId does not belong to the id of the comment author");
        }

    }
}
