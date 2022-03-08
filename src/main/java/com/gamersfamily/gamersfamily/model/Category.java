package com.gamersfamily.gamersfamily.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(
        name = "categories",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Category extends BaseEntity {
    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
