package com.gamersfamily.gamersfamily.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(
        name = "games",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Game extends BaseEntity{

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "game_categories",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories;

    @ElementCollection(targetClass = Platform.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    private Set<Platform> type;
}
