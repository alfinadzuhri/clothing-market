package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.dto.response.GetRewardResponse;
import com.maju_mundur.clothing_market.entity.Reward;
import com.maju_mundur.clothing_market.entity.RewardRules;
import com.maju_mundur.clothing_market.repository.RewardRepository;
import com.maju_mundur.clothing_market.service.RewardRulesService;
import com.maju_mundur.clothing_market.service.RewardService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardRepository rewardRepository;
    private final RewardRulesService rewardRulesService;

    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    private void createInit(){

        if (rewardRepository.count() > 0) {
            return;
        }

        Reward reward = Reward.builder()
                .name("purchase")
                .description("reward for every purchase")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        rewardRepository.saveAndFlush(reward);

        List<RewardRules> rewardRules = List.of(
                RewardRules.builder()
                        .reward(reward)
                        .threshold(3000L)
                        .rewardValue(20)
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .build(),
                RewardRules.builder()
                        .reward(reward)
                        .threshold(5000L)
                        .rewardValue(40)
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .build()
        );

        reward.setRewardRules(rewardRules);
        rewardRulesService.createBulk(rewardRules);
    }

    @Override
    public Reward getRewardByName(String name) {
        return rewardRepository.findByName(name).orElseThrow(() -> new RuntimeException("reward not found"));
    }

    @Override
    public GetRewardResponse getRewardRules(String rewardName, Long threshold) {

        Reward reward = getRewardByName(rewardName);

        List<RewardRules> rewardRules = reward.getRewardRules();

        GetRewardResponse rewardResponse = GetRewardResponse.builder()
                .reward(reward)
                .build();

        rewardRules.sort((a, b) -> b.getThreshold().compareTo(a.getThreshold()));

        for (RewardRules rewardRule : rewardRules) {
            if (threshold >= rewardRule.getThreshold().longValue()) {
                rewardResponse.setRewardValue(rewardRule.getRewardValue());
                break;
            } else {
                rewardResponse.setRewardValue(0);
            }
        }

        return rewardResponse;
    }
}
