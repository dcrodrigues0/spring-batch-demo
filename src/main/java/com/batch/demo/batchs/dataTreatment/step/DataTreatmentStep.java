package com.batch.demo.batchs.dataTreatment.step;

import com.batch.demo.dtos.CustomerAnalyticsDto;
import com.batch.demo.dtos.CustomerEligibleDto;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataTreatmentStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean //It's necessary register stepBuilder with Bean to use it in jobBuilder(DataTreatmentJob.class)
    public Step stepBuilder(JdbcPagingItemReader<CustomerAnalyticsDto> analyticsCustomerReader,
                            FunctionItemProcessor<CustomerAnalyticsDto, CustomerAnalyticsDto> analyticsCustomerProcessor,
                            JdbcBatchItemWriter<CustomerEligibleDto> writeFilteredCustomer) {
        return stepBuilderFactory
                .get("handleCustomerData")
                .<CustomerAnalyticsDto, CustomerEligibleDto>chunk(2)
                .reader(analyticsCustomerReader)
                .processor(analyticsCustomerProcessor) //TODO Fix processor implementation
                .writer(writeFilteredCustomer)
                .build();
    }


}
