package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.dto.CommentDtoOutput;
import com.gamersfamily.gamersfamily.exception.BlogAPIException;
import com.gamersfamily.gamersfamily.mapper.CommentMapper;
import com.gamersfamily.gamersfamily.model.Comment;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.CommentRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import com.gamersfamily.gamersfamily.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepo;
    private final UserRepository userRepo;

    CommentServiceImpl(UserRepository userRepo, CommentMapper commentMapper, CommentRepository commentRepo) {
        this.commentMapper = commentMapper;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;

    }

    @Override
    public CommentDtoOutput saveComment(CommentDto comment) {
        User user = userRepo.findById(comment.getUserId())
                .orElseThrow(() -> {
                    throw new BlogAPIException("no user with given id found", HttpStatus.BAD_REQUEST);
                });
        if (!user.getEmail().equals(getAuthenticatedUserEmail())) {
            throw new BlogAPIException("comment does not belong to the authenticated user", HttpStatus.BAD_REQUEST);
        }
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
                    throw new BlogAPIException("comment with this id can not be found to change", HttpStatus.BAD_REQUEST);
                });
        commentFound.setBody(comment.getBody());
        commentFound.setUpdated(comment.getUpdated());
        if (!commentFound.getAuthor().getEmail().equals(getAuthenticatedUserEmail())) {
            throw new BlogAPIException("comment does not belong to the authenticated user", HttpStatus.BAD_REQUEST);
        } else if (commentFound.getNews().getId() != comment.getNewsId()) {
            throw new BlogAPIException("news id does not belong to this comment", HttpStatus.BAD_REQUEST);
        } else {
            return commentMapper.entityToDto(commentRepo.save(commentFound));
        }
    }

    @Override
    public CommentDtoOutput deleteComment(long commentId, long authorId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> {
            throw new BlogAPIException("comment with this id can not be found to delete", HttpStatus.BAD_REQUEST);
        });
        if (comment.getAuthor().getEmail().equals(getAuthenticatedUserEmail())) {
            CommentDtoOutput output = commentMapper.entityToDto(comment);
            commentRepo.deleteById(commentId);
            return output;
        } else {
            throw new BlogAPIException("comment author does not belong to the authenticated user", HttpStatus.BAD_REQUEST);
        }

    }

    private String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
