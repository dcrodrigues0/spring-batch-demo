package com.batch.demo.batchs;

import com.batch.demo.dtos.CustomerAnalyticsDto;
import com.batch.demo.entitys.CustomerEligible;
import com.batch.demo.repositories.CustomerAnalyticsRepository;
import com.batch.demo.repositories.CustomerEligibleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.toList;

@EnableBatchProcessing
@Configuration
public class DataHandlingBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CustomerAnalyticsRepository customerAnalyticsRepository;

    @Autowired
    private CustomerEligibleRepository customerEligibleRepository;

    @Bean
    public Job jobBuilder(){
        return jobBuilderFactory
                .get("dataHandling") //This is the job name
                .start(stepBuilder()) //Method started by job, start can handle with Steps and Flows
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Step stepBuilder() {
        return stepBuilderFactory
                .get("handleCustomerData")
                .<CustomerAnalyticsDto, CustomerAnalyticsDto>chunk(3)
                .reader(readAnalyticsCustomer())
                .processor(filterCustomer())
                .writer(writeFilteredCustomer())
                .build();
    }

    private IteratorItemReader<CustomerAnalyticsDto> readAnalyticsCustomer() {
        //TODO Change this modelMapper to interface mapper
        ModelMapper modelMapper = new ModelMapper();
        List<CustomerAnalyticsDto> customers =
                customerAnalyticsRepository.findAll()
                        .stream()
                        .map(customerAnalytics -> modelMapper.map(customerAnalytics,CustomerAnalyticsDto.class))
                        .collect(toList());

        return new IteratorItemReader<CustomerAnalyticsDto>(customers);
    }

    public FunctionItemProcessor<CustomerAnalyticsDto, CustomerAnalyticsDto> filterCustomer(){
        return new FunctionItemProcessor<CustomerAnalyticsDto, CustomerAnalyticsDto>
                (customer -> customer.getCity().equals("SP") ? customer:null);
    }

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
