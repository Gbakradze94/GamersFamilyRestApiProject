package com.gamersfamily.gamersfamily.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(
        name = "roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class Role extends BaseEntity{
    @Column(name = "name", nullable = false, length = 60)
    private String name;
}
