package com.maju_mundur.clothing_market.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String merchantUsername;
    private String productId;
    private String productName;
    private String productDescription;
    private Long price;
    private Integer stocks;
}
