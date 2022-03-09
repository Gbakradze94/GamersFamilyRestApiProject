package com.gamersfamily.gamersfamily.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(
        name = "categories",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
//@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(getName(), category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Category{" +
                "ID: " + getId() +
                ", name='" + getName() +
                '}';
    }
}
