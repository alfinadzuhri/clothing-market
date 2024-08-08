package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.entity.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface TransactionDetailService {
    List<TransactionDetail> createBulk (List<TransactionDetail> transactionDetails);
}
