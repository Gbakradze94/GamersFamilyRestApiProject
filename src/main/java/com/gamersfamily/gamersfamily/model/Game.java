package com.gamersfamily.gamersfamily.model;

import com.gamersfamily.gamersfamily.utils.enums.Platform;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(
        name = "games",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Game extends BaseEntity {

    @Column(name = "name", nullable = false, length = 150)
    @NotEmpty(message = "Name of the game cannot be empty")
    private String name;

    @Lob
    @Column(name = "description", columnDefinition = "CLOB")
    private String description;

    @Column(name = "image_url")
    private String background_image;

    @Column(name = "release_date")
    private Date released;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_categories",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories;
//
//    @ElementCollection(targetClass = Platform.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "game_platforms",
//            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
//    @Enumerated(EnumType.STRING)
//    @Column(name = "platform")
//
//    private Set<Platform> type;

    @Override
    public String toString() {
        return "Game{" +
                "ID: " + getId() +
                ", name = '" + getName() +
                '}';
    }

}
