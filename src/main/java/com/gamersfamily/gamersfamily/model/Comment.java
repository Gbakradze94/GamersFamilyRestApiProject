package com.gamersfamily.gamersfamily.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "comment")
public class Comment extends BaseEntity {


    @Builder
    private static Comment of(long id, String body, User author, News news , LocalDateTime createdAt) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.body=body;
        comment.author=author;
        comment.news=news;
        comment.createdAt = createdAt;
        return comment;
    }


   @Lob
    @Column(name = "commentBody", nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;
    @Column(name = "creationTime")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "updateTime")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL,orphanRemoval=true)
    private List<SubComment> subCommentList;


    @Override
    public String toString() {
        return "Comment{" +
                "body='" + body + '\'' +
                ", author=" + author +
                ", news=" + news +
                ", createdAt=" + createdAt +
                ", updated=" + updated +
                '}';
    }
}
