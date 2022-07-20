package com.batch.demo.batchs.dataTreatment.writer;

import com.batch.demo.dtos.CustomerAnalyticsDto;
import com.batch.demo.dtos.CustomerEligibleDto;
import com.batch.demo.entitys.CustomerEligible;
import com.batch.demo.repositories.CustomerEligibleRepository;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ProcessedCustomerWriterConfig {

    @Autowired
    private CustomerEligibleRepository customerEligibleRepository;

    @Bean //Todo implement with jpa
    public JdbcBatchItemWriter<CustomerEligible> writeFilteredCustomer(
            @Qualifier("springDataSource") DataSource dataSource
            ){

        return new JdbcBatchItemWriterBuilder<CustomerEligible>()
                .dataSource(dataSource)
                .sql("INSERT INTO CUSTOMER_ELIGIBLE (CITY, FIRSTNAME, LASTNAME) VALUES (?, ?, ?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<CustomerEligible> itemPreparedStatementSetter() {
        return (customer, preparedStatement) -> {
            preparedStatement.setString(1, customer.getCity());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
        };
    }

}
