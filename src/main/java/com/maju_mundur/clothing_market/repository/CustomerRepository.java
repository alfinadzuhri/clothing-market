package com.maju_mundur.clothing_market.repository;

import com.maju_mundur.clothing_market.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
