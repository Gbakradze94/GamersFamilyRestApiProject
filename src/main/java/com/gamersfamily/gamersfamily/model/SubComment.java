package com.gamersfamily.gamersfamily.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name="subComment")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubComment that = (SubComment) o;
        return Objects.equals(body, that.body) && Objects.equals(author, that.author) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, author, comment);
    }

    @Override
    public String toString() {
        return "SubComment{" +
                "body='" + body + '\'' +
                ", author=" + author +
                ", comment=" + comment +
                '}';
    }
}
