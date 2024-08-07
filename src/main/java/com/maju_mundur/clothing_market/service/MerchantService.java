package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.dto.response.MerchantResponse;
import com.maju_mundur.clothing_market.entity.Merchant;

public interface MerchantService {
    Merchant create(Merchant merchant);
    Merchant getByUsername(String username);
    MerchantResponse parseToMerchantResponse(Merchant merchant);
}
