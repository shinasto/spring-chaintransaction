package com.shinasto.routingdatasource.service;

import com.shinasto.routingdatasource.data.DanjiDto;

public interface DanjiService {
  void saveDanji(DanjiDto danjiDto);

  DanjiDto getMemberById(final Integer id);
}
