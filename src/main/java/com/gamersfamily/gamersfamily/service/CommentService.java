package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.model.Comment;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(CommentDto comment);

    List<CommentDto> getComments(long newsId);

    CommentDto editComment(CommentDto comment);

    CommentDto deleteComment(long commentId);
}
