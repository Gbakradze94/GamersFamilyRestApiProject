package com.gamersfamily.gamersfamily.model;


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
        name = "roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Role extends BaseEntity {
    @Column(name = "name", nullable = false, length = 60)
    private String name;


    @Override
    public String toString() {
        return "Role{" +
                "ID: " + getId() +
                ", name = '" + getName() +
                '}';
    }
}
