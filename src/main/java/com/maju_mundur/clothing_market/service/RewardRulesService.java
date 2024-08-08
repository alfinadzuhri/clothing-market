package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.entity.RewardRules;

import java.util.List;

public interface RewardRulesService {
    List<RewardRules> createBulk(List<RewardRules> rewardRules);
}
