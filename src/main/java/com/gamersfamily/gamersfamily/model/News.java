package com.gamersfamily.gamersfamily.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(
        name = "news",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class News extends BaseEntity {
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "body", nullable = false)
    private String body;
    @OneToMany(mappedBy = "news")
    private List<Comment> comments;
    @OneToMany(mappedBy = "news")
    private List<Rating> ratings;
}
