package com.gamersfamily.gamersfamily.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "comment")
    private List<SubComment> subCommentList;


    @Override
    public String toString() {
        return "Comment{" +
                "ID: " + getId() +
                ", body='" + getBody() + '\'' +
                ", author=" + getAuthor() +
                ", news=" + getNews() +
                '}';
    }
}
