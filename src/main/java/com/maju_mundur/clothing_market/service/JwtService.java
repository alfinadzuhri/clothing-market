package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.dto.response.JwtClaims;
import com.maju_mundur.clothing_market.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);

    boolean verifyJwtToken(String token);

    JwtClaims getClaimsByToken(String token);
}
