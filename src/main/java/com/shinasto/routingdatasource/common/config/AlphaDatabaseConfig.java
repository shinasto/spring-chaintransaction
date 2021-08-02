package com.shinasto.routingdatasource.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.shinasto.routingdatasource.database.master.repository",
    entityManagerFactoryRef = "masterEntityManagerFactory",
    transactionManagerRef = "masterTransactionManager")
public class AlphaDatabaseConfig {

  //@Primary
  @Bean
  public DataSource masterDataSource() {
    return new LazyConnectionDataSourceProxy(new HikariDataSource(createMasterDataSource()));
  }

  //@Primary
  @Bean(name = "masterEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(masterDataSource());
    em.setPackagesToScan("com.shinasto.routingdatasource.database.master.entity");
    em.setPersistenceUnitName("master");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);

    // Hibernate 설정
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
    em.setJpaPropertyMap(properties);

    return em;
  }

  //@Primary
  @Bean(name = "masterTransactionManager")
  public PlatformTransactionManager masterTransactionManager() {
    return new JpaTransactionManager(Objects.requireNonNull(masterEntityManagerFactory().getObject()));
  }

  @Bean
  @ConfigurationProperties(prefix = "danji.datasource.master")
  public HikariConfig createMasterDataSource() {
    return new HikariConfig();
  }


}
