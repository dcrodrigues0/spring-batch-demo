package com.batch.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-private.properties")
public class MainConfiguration {

}
