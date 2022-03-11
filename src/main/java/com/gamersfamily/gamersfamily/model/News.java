package com.gamersfamily.gamersfamily.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(
        name = "news",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class News extends BaseEntity {

    @Builder
    private static News of(long id, String name, String body) {
        News news = new News();
        news.setId(id);
        news.body = body;
        news.name = name;
        return news;
    }

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Lob
    @Column(name = "body", columnDefinition = "CLOB", nullable = false)
    private String body;
    @OneToMany(mappedBy = "news")
    private List<Comment> comments;
    @OneToMany(mappedBy = "news")
    private List<Rating> ratings;


    @Override
    public String toString() {
        return "News{" +
                "ID: " + getId() +
                ", name='" + getName() + '\'' +
                ", body=" + getBody() +
                '}';
    }

}
