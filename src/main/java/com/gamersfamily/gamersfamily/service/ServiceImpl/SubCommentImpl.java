package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.dto.SubCommentDto;
import com.gamersfamily.gamersfamily.dto.SubCommentDtoOutput;
import com.gamersfamily.gamersfamily.mapper.SubCommentMapper;
import com.gamersfamily.gamersfamily.model.SubComment;
import com.gamersfamily.gamersfamily.repository.SubCommentRepository;
import com.gamersfamily.gamersfamily.service.SubCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCommentImpl implements SubCommentService {

    private SubCommentRepository subCommentRepository;
    private SubCommentMapper subCommentMapper;

    SubCommentImpl(SubCommentRepository subCommentRepository, SubCommentMapper subCommentMapper) {
        this.subCommentMapper = subCommentMapper;
        this.subCommentRepository = subCommentRepository;

    }

    @Override
    public SubCommentDtoOutput saveSubComment(SubCommentDto subComment) {
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
                    throw new IllegalArgumentException("subcomment with this id can not be found to change");
                });
        subCommentFound.setBody(subComment.getBody());
        subCommentFound.setUpdated(subComment.getUpdated());
        if (subCommentFound.getAuthor().getId() == subComment.getUserId() && subCommentFound.getComment().getId() == subComment.getCommentId())
            return subCommentMapper.entityToDto(subCommentRepository.save(subCommentFound));
        else throw new IllegalArgumentException("newsId or subCommentId is not correct");

    }

    @Override
    public SubCommentDtoOutput deleteSubComment(long subCommentId, long authorId) {

        SubComment subComment = subCommentRepository.findById(subCommentId).orElseThrow(() -> {
            throw new IllegalArgumentException("subComment with this id can not be found to delete");
        });
        if (subComment.getAuthor().getId() == authorId) {
            SubCommentDtoOutput output = subCommentMapper.entityToDto(subComment);
            subCommentRepository.delete(subComment);
            return output;
        } else {
            throw new IllegalArgumentException("authorId does not belong to the id of the subComment author");
        }
    }
}
