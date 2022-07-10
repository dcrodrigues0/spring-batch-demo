package com.batch.demo.configs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job demoBatch(){
        return jobBuilderFactory
                .get("demo") //This is the job name
                .start(printMessage()) //Method started by job, start can handle with Steps and Flows
                .start(printMessage2())
                .build();
    }

    private Step printMessage() {
        return stepBuilderFactory
                .get("printMessage")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Message printed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step printMessage2() {
        return stepBuilderFactory
                .get("printMessage")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Message printed 2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
