package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.dto.response.GetRewardResponse;
import com.maju_mundur.clothing_market.entity.Customer;
import com.maju_mundur.clothing_market.entity.OrderedReward;

public interface OrderedRewardService {

    OrderedReward getByCustomerId (String customerId);
    void updatedPoints (Customer customer, GetRewardResponse getRewardResponse);

}
