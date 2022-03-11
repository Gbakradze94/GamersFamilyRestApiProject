package com.gamersfamily.gamersfamily.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "subComment")
public class SubComment extends BaseEntity {

    @Lob
    @Column(name = "subCommentBody", columnDefinition = "CLOB", nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Column(name="creationTime")
    private LocalDateTime createdAt= LocalDateTime.now();
    @Column(name="updateTime")
    private LocalDateTime updated;

    @Override
    public String toString() {
        return "SubComment{" +
                "body='" + body + '\'' +
                ", author=" + author +
                ", comment=" + comment +
                ", createdAt=" + createdAt +
                ", updated=" + updated +
                '}';
    }
}
