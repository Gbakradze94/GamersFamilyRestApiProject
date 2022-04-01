package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.dto.SubCommentDto;
import com.gamersfamily.gamersfamily.dto.SubCommentDtoOutput;
import com.gamersfamily.gamersfamily.exception.BlogAPIException;
import com.gamersfamily.gamersfamily.mapper.SubCommentMapper;
import com.gamersfamily.gamersfamily.model.SubComment;
import com.gamersfamily.gamersfamily.model.User;
import com.gamersfamily.gamersfamily.repository.SubCommentRepository;
import com.gamersfamily.gamersfamily.repository.UserRepository;
import com.gamersfamily.gamersfamily.service.SubCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCommentImpl implements SubCommentService {

    private final SubCommentRepository subCommentRepository;
    private final SubCommentMapper subCommentMapper;
    private final UserRepository userRepo;


    SubCommentImpl(UserRepository userRepo, SubCommentRepository subCommentRepository, SubCommentMapper subCommentMapper) {
        this.subCommentMapper = subCommentMapper;
        this.subCommentRepository = subCommentRepository;
        this.userRepo = userRepo;

    }

    @Override
    public SubCommentDtoOutput saveSubComment(SubCommentDto subComment) {
        User user = userRepo.findById(subComment.getUserId())
                .orElseThrow(() -> {
                    throw new BlogAPIException("no user with given id found", HttpStatus.BAD_REQUEST);
                });
        if (!user.getEmail().equals(getAuthenticatedUserEmail()) && user.isEnabled()) {
            throw new BlogAPIException("subComment does not belong to the authenticated user", HttpStatus.BAD_REQUEST);
        }
        return subCommentMapper.entityToDto(subCommentRepository.save(subCommentMapper.dtoToEntity(subComment)));

    }

    @Override
    public List<SubCommentDtoOutput> getSubComments(long commentId) {
        return subCommentRepository.findByCommentId(commentId)
                .stream().map(subcom -> subCommentMapper.entityToDto(subcom)).toList();
    }

    @Override
    public SubCommentDtoOutput editSubComment(SubCommentDtoOutput subComment) {
        SubComment subCommentFound = subCommentRepository.findById(subComment.getId())
                .orElseThrow(() -> {
                    throw new BlogAPIException("subComment with this id can not be found to change", HttpStatus.BAD_REQUEST);
                });
        subCommentFound.setBody(subComment.getBody());
        subCommentFound.setUpdated(subComment.getUpdated());
        if (!subCommentFound.getAuthor().getEmail().equals(getAuthenticatedUserEmail())  && subCommentFound.getAuthor().isEnabled() ) {
            throw new BlogAPIException("subComment author does not belong to the authenticated user", HttpStatus.BAD_REQUEST);
        } else if (subCommentFound.getComment().getId() != subComment.getCommentId()) {
            throw new BlogAPIException("subcomment id does not belong to this comment", HttpStatus.BAD_REQUEST);
        } else {
            return subCommentMapper.entityToDto(subCommentRepository.save(subCommentFound));
        }

    }

    @Override
    public SubCommentDtoOutput deleteSubComment(long subCommentId, long authorId) {

        SubComment subComment = subCommentRepository.findById(subCommentId).orElseThrow(() -> {
            throw new BlogAPIException("subComment with this id can not be found to delete", HttpStatus.BAD_REQUEST);
        });
        if (subComment.getAuthor().getEmail().equals(getAuthenticatedUserEmail()) && subComment.getAuthor().isEnabled()) {
            SubCommentDtoOutput output = subCommentMapper.entityToDto(subComment);
            subCommentRepository.delete(subComment);
            return output;
        } else {
            throw new BlogAPIException("Subcomment does not belong to the authenticated user", HttpStatus.BAD_REQUEST);
        }
    }

    private String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
