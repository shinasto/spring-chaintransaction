package com.shinasto.routingdatasource.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
    basePackages = {"com.shinasto.routingdatasource.database.slave.repository"},
    entityManagerFactoryRef = "slaveEntityManagerFactory",
    transactionManagerRef = "slaveTransactionManager"
)
public class BetaDatabaseConfig {

  @Bean
  @ConfigurationProperties(prefix = "danji.datasource.slave")
  public HikariConfig slaveDataConfig() {
    return new HikariConfig();
  }

  @Bean(name = "slaveDataSource")
  public DataSource slaveDataSource() {
    return new LazyConnectionDataSourceProxy(new HikariDataSource(slaveDataConfig()));
  }

  /* -----------------JPA 셋팅------------------------------------- */
  @Bean(name = "slaveEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(slaveDataSource());
    // Entity package 경로
    em.setPackagesToScan(new String[] {"com.shinasto.routingdatasource.database.slave.entity"});
    em.setPersistenceUnitName("slave");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);

    // Hibernate 설정
    HashMap<String, Object> properties = new HashMap<>();
    //properties.put("hibernate.hdm2ddl.auto", false);
    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
    em.setJpaPropertyMap(properties);

    return em;
  }


  @Bean(name = "slaveTransactionManager")
  public PlatformTransactionManager slaveTransactionManager() {
    JpaTransactionManager tm = new JpaTransactionManager();
    tm.setEntityManagerFactory(slaveEntityManagerFactory().getObject());
    return tm;
  }
}
