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
    @Column(name = "body", nullable = false)
    private String body;
    @OneToMany(mappedBy = "news")
    private List<Comment> comments;
    @OneToMany(mappedBy = "news")
    private List<Rating> ratings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(getName(), news.getName()) &&
                Objects.equals(getBody(), news.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBody());
    }

    @Override
    public String toString() {
        return "News{" +
                "ID: " + getId() +
                ", name='" + getName() + '\'' +
                ", body=" + getBody() +
                '}';
    }

}
