package com.gamersfamily.gamersfamily.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(
        name = "roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity{
    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(getName(), role.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Role{" +
                "ID: " + getId() +
                ", name = '" + getName() +
                '}';
    }
}
