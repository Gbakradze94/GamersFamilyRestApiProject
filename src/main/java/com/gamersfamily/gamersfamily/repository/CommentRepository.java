package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
