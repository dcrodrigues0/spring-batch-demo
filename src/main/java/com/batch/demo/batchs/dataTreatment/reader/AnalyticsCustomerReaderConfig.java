package com.batch.demo.batchs.dataTreatment.reader;

import com.batch.demo.dtos.CustomerAnalyticsDto;
import com.batch.demo.entitys.CustomerAnalytics;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class AnalyticsCustomerReaderConfig {

    @Bean
    public JdbcPagingItemReader<CustomerAnalyticsDto> analyticsCustomerReader(
            @Qualifier("analyticsDataSource") DataSource dataSource,
            PagingQueryProvider queryProvider){

        //TODO Change it to JPAPagingItemReader
        return new JdbcPagingItemReaderBuilder<CustomerAnalyticsDto>()
                .name("analyticsCustomerReader")
                .dataSource(dataSource)
                .queryProvider(queryProvider)
                .pageSize(1)
                .rowMapper(new BeanPropertyRowMapper<CustomerAnalyticsDto>(CustomerAnalyticsDto.class))
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(
            @Qualifier("analyticsDataSource") DataSource dataSource){

        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("SELECT *");
        queryProvider.setFromClause("FROM CUSTOMER_ANALYTICS");
        queryProvider.setSortKey("ID");
        return queryProvider;
    }

}
