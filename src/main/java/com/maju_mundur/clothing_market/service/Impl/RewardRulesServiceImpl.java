package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.entity.RewardRules;
import com.maju_mundur.clothing_market.repository.RewardRulesRepository;
import com.maju_mundur.clothing_market.service.RewardRulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardRulesServiceImpl implements RewardRulesService {

    private final RewardRulesRepository rewardRulesRepository;

    @Override
    public List<RewardRules> createBulk(List<RewardRules> rewardRules) {
        return rewardRulesRepository.saveAllAndFlush(rewardRules);
    }
}
