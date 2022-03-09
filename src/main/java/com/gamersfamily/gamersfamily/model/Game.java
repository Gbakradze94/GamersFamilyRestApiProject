package com.gamersfamily.gamersfamily.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

//@Data
@Getter
@Setter
@Entity
@Table(
        name = "games",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
//@EqualsAndHashCode(callSuper = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(getName(), game.getName()) &&
                Objects.equals(getDescription(), game.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }

    @Override
    public String toString() {
        return "Game{" +
                "ID: " + getId() +
                ", name = '" + getName() + '\'' +
                ", description = " + getDescription() +
                '}';
    }

}
