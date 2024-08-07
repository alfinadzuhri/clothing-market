package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.entity.Customer;
import com.maju_mundur.clothing_market.repository.CustomerRepository;
import com.maju_mundur.clothing_market.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

}
