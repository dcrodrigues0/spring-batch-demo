package com.batch.demo.repositories;

import com.batch.demo.entitys.CustomerEligible;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerEligibleRepository extends JpaRepository<CustomerEligible, Integer> {

}
