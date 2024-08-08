package com.maju_mundur.clothing_market.repository;

import com.maju_mundur.clothing_market.entity.OrderedReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderedRewardRepository extends JpaRepository<OrderedReward, String> {
    Optional<OrderedReward> findByCustomerId (String customerId);
}
