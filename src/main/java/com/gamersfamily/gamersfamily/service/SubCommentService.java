package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.SubCommentDto;
import com.gamersfamily.gamersfamily.model.Comment;

import java.util.List;

public interface SubCommentService {

    Comment saveComment(SubCommentDto subComment);

    List<Comment> getComments(long commentId);

    Comment editComment(SubCommentDto subComment);

    Comment deleteComment(long commentId);
}
