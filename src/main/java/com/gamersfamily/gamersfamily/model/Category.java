package com.gamersfamily.gamersfamily.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(
        name = "categories",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;


    @Override
    public String toString() {
        return "Category{" +
                "ID: " + getId() +
                ", name='" + getName() +
                '}';
    }
}
