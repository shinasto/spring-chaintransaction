package com.shinasto.routingdatasource.service;

import com.shinasto.routingdatasource.data.DanjiDto;
import com.shinasto.routingdatasource.database.master.entity.Danji;
import com.shinasto.routingdatasource.database.master.repository.DanjiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Optional;

@Slf4j
@Service
public class DanjiServiceImpl implements DanjiService {
  @Autowired DanjiRepository danjiRepository;

  @Transactional
  public void saveDanji(DanjiDto danjiDto) {
    log.info("TX Name={}", TransactionSynchronizationManager.getCurrentTransactionName());

    Danji danji = new Danji();
    danji.setName(danjiDto.getName());

    danjiRepository.save(danji);
  }

  @Transactional(readOnly = true)
  public DanjiDto getMemberById(final Integer id) {

    log.info("TX Name={}", TransactionSynchronizationManager.getCurrentTransactionName());

    Optional<Danji> optDanji = this.danjiRepository.findById(1);
    if(optDanji.isPresent()) {

      Danji danji = optDanji.get();
      log.info("Danji Id={}, Name={}", danji.getId(), danji.getName());
      return new DanjiDto(danji.getId(), danji.getName());
    }

    return null;
  }
}
