package com.gamersfamily.gamersfamily.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name="comment")
public class Comment extends BaseEntity {
    @Column(name = "commentBody", nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;
    @Column(name="creationTime")
    private LocalDateTime createdAt= LocalDateTime.now();
    @Column(name="updateTime")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "comment")
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
