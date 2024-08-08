package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.dto.response.GetRewardResponse;
import com.maju_mundur.clothing_market.entity.Customer;
import com.maju_mundur.clothing_market.entity.OrderedReward;
import com.maju_mundur.clothing_market.repository.OrderedRewardRepository;
import com.maju_mundur.clothing_market.service.OrderedRewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderedRewardServiceImpl implements OrderedRewardService {

    private final OrderedRewardRepository orderedRewardRepository;

    @Override
    public OrderedReward getByCustomerId(String customerId) {

        Optional<OrderedReward> orderedReward = orderedRewardRepository.findByCustomerId(customerId);
       if (orderedReward.isPresent()) {
           return orderedReward.get();
       } else {
           return null;
       }
    }

    @Override
    public void updatedPoints(Customer customer, GetRewardResponse getRewardResponse) {
        OrderedReward orderedReward = getByCustomerId(customer.getId());
        if (orderedReward == null) {

            orderedRewardRepository.saveAndFlush(OrderedReward.builder()
                    .customer(customer)
                    .points(getRewardResponse.getRewardValue())
                    .reward(getRewardResponse.getReward())
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build());

        } else {
            orderedReward.setPoints(orderedReward.getPoints() + getRewardResponse.getRewardValue());
            orderedReward.setUpdatedAt(new Date());
            orderedRewardRepository.saveAndFlush(orderedReward);
        }
    }
}
