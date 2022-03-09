package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
