package com.shinasto.routingdatasource.database.slave.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  private String name;

  private String age;
}
