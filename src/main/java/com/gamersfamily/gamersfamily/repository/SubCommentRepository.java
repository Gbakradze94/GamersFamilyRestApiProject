package com.gamersfamily.gamersfamily.repository;

import com.gamersfamily.gamersfamily.model.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {
    List<SubComment> findByCommentId(long id);
}
