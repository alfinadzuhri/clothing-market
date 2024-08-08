package com.maju_mundur.clothing_market.repository;

import com.maju_mundur.clothing_market.entity.Customer;
import com.maju_mundur.clothing_market.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByUserAccountId(String id);
}
