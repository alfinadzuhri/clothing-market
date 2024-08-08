package com.maju_mundur.clothing_market.dto.response;

import com.maju_mundur.clothing_market.entity.Transaction;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    private String transactionId;
    private String username;
    private Date transactionDate;
    private List<TransactionDetailResponse> transactionDetails;
    private Long totalPayment;
    private Integer totalQuantity;
    private Integer rewardPoints;

}
