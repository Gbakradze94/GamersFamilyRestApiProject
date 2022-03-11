package com.gamersfamily.gamersfamily.model;


import com.gamersfamily.gamersfamily.utils.enums.Rate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name="rating")
public class Rating extends BaseEntity {

    @Column(name = "rate", nullable = false)
    private Rate rate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;


    @Override
    public String toString() {
        return "Rating{" +
                "rate=" + rate +
                ", author=" + author +
                ", news=" + news +
                '}';
    }
}
