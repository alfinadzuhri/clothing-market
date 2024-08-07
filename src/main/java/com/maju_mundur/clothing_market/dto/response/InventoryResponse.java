package com.maju_mundur.clothing_market.dto.response;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    private String inventoryId;
    private MerchantResponse merchantResponse;
    private ProductResponse productResponse;
    private Integer stocks;
    private Long price;
    private Date createdAt;
}
