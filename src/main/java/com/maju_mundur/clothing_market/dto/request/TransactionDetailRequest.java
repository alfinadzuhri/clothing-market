package com.maju_mundur.clothing_market.dto.request;

import com.maju_mundur.clothing_market.entity.Product;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetailRequest {
    private String productId;
    private Integer quantity;
}
