package com.maju_mundur.clothing_market.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantResponse {
    private String merchantId;
    private String merchantUsername;
    private String merchantName;
    private String merchantPhoneNumber;
    private String merchantAddress;
}
