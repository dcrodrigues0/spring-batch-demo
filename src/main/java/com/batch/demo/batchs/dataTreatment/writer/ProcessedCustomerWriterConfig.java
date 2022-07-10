package com.batch.demo.batchs.dataTreatment.writer;

import com.batch.demo.dtos.CustomerAnalyticsDto;
import com.batch.demo.entitys.CustomerEligible;
import com.batch.demo.repositories.CustomerEligibleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.stream.Collectors.toList;

@Configuration
public class ProcessedCustomerWriterConfig {

    @Autowired
    private CustomerEligibleRepository customerEligibleRepository;

    @Bean
    public ItemWriter<CustomerAnalyticsDto> writeFilteredCustomer(){
        return customers -> {
            ModelMapper modelMapper = new ModelMapper();
            customerEligibleRepository.saveAll(
                    customers
                            .stream()
                            .map(customerAnalyticsDto -> modelMapper.map(customerAnalyticsDto, CustomerEligible.class))
                            .collect(toList())
            );
            customers.forEach(System.out::println);
        };
    }

}
