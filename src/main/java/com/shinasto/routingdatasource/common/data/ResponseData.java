package com.shinasto.routingdatasource.common.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
  private Integer resultCode;
  private String resultMessage;
  private Object data;

  public static ResponseData of(Object data) {
    return new ResponseData(1, null, data);
  }
}
