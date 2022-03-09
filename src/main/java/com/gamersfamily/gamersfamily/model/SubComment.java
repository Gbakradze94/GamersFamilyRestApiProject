package com.gamersfamily.gamersfamily.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "subComment")
public class SubComment extends BaseEntity {
    @Column(name = "subCommentBody", nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;


    @Override
    public String toString() {
        return "SubComment{" +
                "ID: " + getId() +
                ", body='" + getBody() + '\'' +
                ", author=" + getAuthor() +
                ", comment=" + getComment() +
                '}';
    }
}
