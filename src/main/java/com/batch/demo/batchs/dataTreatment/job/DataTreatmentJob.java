package com.batch.demo.batchs.dataTreatment.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class DataTreatmentJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job jobBuilder(Step stepBuilder){
        return jobBuilderFactory
                .get("dataTreatment") //This is the job name
                .start(stepBuilder) //Method started by job, start can handle with Steps and Flows
                .incrementer(new RunIdIncrementer())
                .build();
    }


}
