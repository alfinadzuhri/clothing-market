package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.dto.response.GetRewardResponse;
import com.maju_mundur.clothing_market.entity.Reward;
import com.maju_mundur.clothing_market.entity.RewardRules;

public interface RewardService {
    Reward getRewardByName(String name);
    GetRewardResponse getRewardRules(String rewardName, Long threshold);
}
