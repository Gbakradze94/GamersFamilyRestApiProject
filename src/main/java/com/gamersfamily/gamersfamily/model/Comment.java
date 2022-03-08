package com.gamersfamily.gamersfamily.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table
public class Comment extends BaseEntity{

   private String body;
}
