package com.maju_mundur.clothing_market.dto.request;

import com.maju_mundur.clothing_market.entity.TransactionDetail;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private List<TransactionDetailRequest> transactionDetailRequests;
}
