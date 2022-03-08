package com.gamersfamily.gamersfamily.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(body, comment.body) && Objects.equals(author, comment.author) && Objects.equals(news, comment.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, author, news);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "body='" + body + '\'' +
                ", author=" + author +
                ", news=" + news +
                '}';
    }
}
