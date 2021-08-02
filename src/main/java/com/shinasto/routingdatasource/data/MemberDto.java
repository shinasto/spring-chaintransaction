package com.shinasto.routingdatasource.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
  private Integer id;
  private String name;
  private String age;
}
