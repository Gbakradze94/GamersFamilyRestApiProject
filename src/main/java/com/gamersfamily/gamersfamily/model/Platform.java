package com.gamersfamily.gamersfamily.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(
        name = "platforms",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Platform extends BaseEntity{
    @Column(name = "name", nullable = false, length = 150)
    @NotEmpty(message = "Name of the platform cannot be empty")
    private String name;

    @Override
    public String toString() {
        return "Platform{" +
                "ID: " + getId() +
                ", name = '" + getName() +
                '}';
    }
}
