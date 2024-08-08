package com.maju_mundur.clothing_market.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailResponse {

    private String productName;
    private Long price;
    private Integer quantity;

}
