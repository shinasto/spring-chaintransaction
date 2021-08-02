package com.shinasto.routingdatasource.database.master.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Danji {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
}
