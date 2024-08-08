package com.maju_mundur.clothing_market.repository;

import com.maju_mundur.clothing_market.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RewardRepository extends JpaRepository<Reward, String> {
    Optional<Reward> findByName(String name);
}
