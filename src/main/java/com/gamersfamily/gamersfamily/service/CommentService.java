package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.model.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment(CommentDto comment);

    List<Comment> getComments(long newsId);

    Comment editComment(CommentDto comment);

    Comment deleteComment(long commentId);
}
