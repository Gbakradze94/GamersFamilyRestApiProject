package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.SubCommentDto;
import com.gamersfamily.gamersfamily.dto.SubCommentDtoOutput;

import java.util.List;

public interface SubCommentService {

    SubCommentDtoOutput saveSubComment(SubCommentDto subComment);

    List<SubCommentDtoOutput> getSubComments(long CommentId);

    SubCommentDtoOutput editSubComment(SubCommentDtoOutput subComment);

    SubCommentDtoOutput deleteSubComment(long subCommentId, long authorId);
}
