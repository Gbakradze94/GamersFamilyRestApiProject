package com.gamersfamily.gamersfamily.model;


import com.gamersfamily.gamersfamily.converter.RateConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name="rating")
public class Rating extends BaseEntity {

    @Column(name = "rate", nullable = false)
    @Convert(converter = RateConverter.class)
    private Rate rate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return rate == rating.rate && Objects.equals(author, rating.author) && Objects.equals(news, rating.news);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate, author, news);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "rate=" + rate +
                ", author=" + author +
                ", news=" + news +
                '}';
    }
}
