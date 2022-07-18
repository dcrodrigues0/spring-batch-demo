package com.batch.demo.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "analytics.datasource")
    public DataSource analyticsDataSource(){
        return DataSourceBuilder.create().build();
    }



}
