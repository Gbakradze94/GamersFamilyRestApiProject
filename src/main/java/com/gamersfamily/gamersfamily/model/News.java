package com.gamersfamily.gamersfamily.model;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(
        name = "news",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class News extends BaseEntity {

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
