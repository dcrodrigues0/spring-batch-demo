package com.batch.demo.repositories;

import com.batch.demo.entitys.CustomerAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAnalyticsRepository extends JpaRepository<CustomerAnalytics, Integer> {



}
