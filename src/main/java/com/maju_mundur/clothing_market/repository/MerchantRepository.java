package com.maju_mundur.clothing_market.repository;

import com.maju_mundur.clothing_market.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {
    Optional<Merchant> findByUserAccountId(String username);
}
