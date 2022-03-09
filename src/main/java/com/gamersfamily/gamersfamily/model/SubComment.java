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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubComment that = (SubComment) o;
        return Objects.equals(getBody(), that.getBody())
                && Objects.equals(getAuthor(), that.getAuthor())
                && Objects.equals(getComment(), that.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBody(), getAuthor(), getComment());
    }

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
