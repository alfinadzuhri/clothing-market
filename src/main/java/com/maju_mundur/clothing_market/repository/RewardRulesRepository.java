package com.maju_mundur.clothing_market.repository;

import com.maju_mundur.clothing_market.entity.RewardRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RewardRulesRepository extends JpaRepository<RewardRules, String> {
}
