package com.batch.demo.batchs.dataTreatment.reader;

import com.batch.demo.dtos.CustomerAnalyticsDto;
import com.batch.demo.repositories.CustomerAnalyticsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Configuration
public class AnalyticsCustomerReaderConfig {

    @Autowired
    private CustomerAnalyticsRepository customerAnalyticsRepository;

    @Bean
    public IteratorItemReader<CustomerAnalyticsDto> analyticsCustomerReader(){
        //TODO Change this modelMapper to interface mapper
        //TODO Handle with pagination here
        ModelMapper modelMapper = new ModelMapper();
        List<CustomerAnalyticsDto> customers =
                customerAnalyticsRepository.findAll()
                        .stream()
                        .map(customerAnalytics -> modelMapper.map(customerAnalytics,CustomerAnalyticsDto.class))
                        .collect(toList());

        return new IteratorItemReader<CustomerAnalyticsDto>(customers);
    }

}
