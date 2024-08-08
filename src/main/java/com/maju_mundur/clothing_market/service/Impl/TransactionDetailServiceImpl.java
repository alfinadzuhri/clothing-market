package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.entity.TransactionDetail;
import com.maju_mundur.clothing_market.repository.TransactionDetailRepository;
import com.maju_mundur.clothing_market.service.TransactionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository transactionDetailRepository;

    @Override
    public List<TransactionDetail> createBulk(List<TransactionDetail> transactionDetails) {
        return transactionDetailRepository.saveAllAndFlush(transactionDetails);
    }
}
