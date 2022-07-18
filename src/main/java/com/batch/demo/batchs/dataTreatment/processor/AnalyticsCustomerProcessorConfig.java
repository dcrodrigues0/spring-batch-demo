package com.batch.demo.batchs.dataTreatment.processor;

import com.batch.demo.dtos.CustomerAnalyticsDto;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalyticsCustomerProcessorConfig {

    @Bean
    public FunctionItemProcessor<CustomerAnalyticsDto, CustomerAnalyticsDto> analyticsCustomerProcessor(){
        return new FunctionItemProcessor<CustomerAnalyticsDto, CustomerAnalyticsDto>
                (customer -> customer.getFirstName().equals("SP") ? customer:null);
    }

}
