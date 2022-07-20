package com.batch.demo.batchs.dataTreatment.processor;

import com.batch.demo.dtos.CustomerAnalyticsDto;
import com.batch.demo.entitys.CustomerAnalytics;
import com.batch.demo.entitys.CustomerEligible;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalyticsCustomerProcessorConfig {

    @Bean
    public FunctionItemProcessor<CustomerAnalyticsDto, CustomerEligible> analyticsCustomerProcessor(){
        return new FunctionItemProcessor<CustomerAnalyticsDto, CustomerEligible>
                (customer -> {
                    ModelMapper mapper = new ModelMapper();
                    CustomerEligible customerEligible = mapper.map(customer, CustomerEligible.class);
                    return customer.getCity().equals("SP") ? customerEligible:null;
                });
    }

}
