package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.dto.request.TransactionRequest;
import com.maju_mundur.clothing_market.dto.response.TransactionResponse;
import com.maju_mundur.clothing_market.entity.Transaction;

public interface TransactionService {
    TransactionResponse create (TransactionRequest transactionRequest);
}
