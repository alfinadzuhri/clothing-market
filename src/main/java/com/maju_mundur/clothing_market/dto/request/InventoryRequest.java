package com.maju_mundur.clothing_market.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryRequest {
    private String merchantUsername;
    private String productName;
    private String productDescription;
    private Long price;
    private Integer stocks;
}
