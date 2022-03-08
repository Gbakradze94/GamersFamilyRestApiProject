package com.gamersfamily.gamersfamily.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(
        name = "roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity{
    @Column(name = "name", nullable = false, length = 60)
    private String name;
}
