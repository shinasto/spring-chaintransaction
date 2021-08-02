package com.shinasto.routingdatasource.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionConfig {
  @Bean
  @Primary
  public PlatformTransactionManager transactionManager(PlatformTransactionManager masterTransactionManager, PlatformTransactionManager slaveTransactionManager) {
    return new ChainedTransactionManager(masterTransactionManager, slaveTransactionManager);
  }
}
