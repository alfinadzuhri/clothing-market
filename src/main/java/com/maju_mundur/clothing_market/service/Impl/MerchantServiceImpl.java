package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.dto.response.MerchantResponse;
import com.maju_mundur.clothing_market.entity.Merchant;
import com.maju_mundur.clothing_market.entity.UserAccount;
import com.maju_mundur.clothing_market.repository.MerchantRepository;
import com.maju_mundur.clothing_market.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final UserAccountServiceImpl userAccountService;

    @Override
    public Merchant create(Merchant merchant) {
        return merchantRepository.saveAndFlush(merchant);
    }

    @Override
    public Merchant getByUsername(String username) {

        UserAccount userAccount = userAccountService.getByUsername(username);

        return merchantRepository.findByUserAccountId(userAccount.getId()).orElseThrow(
                () -> new RuntimeException("merchant not found")
        );
    }

    @Override
    public MerchantResponse parseToMerchantResponse(Merchant merchant) {
        return MerchantResponse.builder()
                .merchantId(merchant.getId())
                .merchantUsername(merchant.getUserAccount().getUsername())
                .merchantName(merchant.getName())
                .merchantPhoneNumber(merchant.getMobilePhoneNo())
                .merchantAddress(merchant.getAddress())
                .build();
    }
}
