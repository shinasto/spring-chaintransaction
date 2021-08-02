package com.shinasto.routingdatasource.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Integer id;
  private String name;
  private String age;
  private String address;
}
