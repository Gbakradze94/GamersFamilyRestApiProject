package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.dto.CommentDto;
import com.gamersfamily.gamersfamily.dto.CommentDtoOutput;
import com.gamersfamily.gamersfamily.model.Comment;

import java.util.List;

public interface CommentService {
    CommentDtoOutput saveComment(CommentDto comment);

    List<CommentDtoOutput> getComments(long newsId);

    CommentDtoOutput editComment(CommentDtoOutput comment);

    CommentDtoOutput deleteComment(long commentId, long authorId);
}
