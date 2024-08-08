package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.dto.request.TransactionRequest;
import com.maju_mundur.clothing_market.dto.response.GetRewardResponse;
import com.maju_mundur.clothing_market.dto.response.TransactionDetailResponse;
import com.maju_mundur.clothing_market.dto.response.TransactionResponse;
import com.maju_mundur.clothing_market.entity.*;
import com.maju_mundur.clothing_market.repository.TransactionRepository;
import com.maju_mundur.clothing_market.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDetailService transactionDetailService;
    private final ProductService productService;
    private final OrderedHistoryService orderedHistoryService;
    private final OrderedRewardService orderedRewardService;
    private final RewardService rewardService;
    private final CustomerService customerService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransactionResponse create(TransactionRequest transactionRequest) {

        Customer customer = customerService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        Transaction transaction = Transaction.builder()
                .customer(customer)
                .transactionDate(new Date())
                .build();

        transactionRepository.saveAndFlush(transaction);

        AtomicReference<Long> totalPrice = new AtomicReference<>(0L);
        AtomicReference<Integer> totalQuantity = new AtomicReference<>(0);

        List<TransactionDetail> transactionDetails = transactionRequest.getTransactionDetailRequests().stream().map(
                transactionDetailRequest -> {

                    Product product  = productService.getById(transactionDetailRequest.getProductId());

                    totalPrice.updateAndGet(v -> v + product.getPrice() * transactionDetailRequest.getQuantity());

                    totalQuantity.updateAndGet(v -> v + transactionDetailRequest.getQuantity());

                    return  TransactionDetail.builder()
                            .transaction(transaction)
                            .product(product)
                            .quantity(transactionDetailRequest.getQuantity())
                            .createdAt(new Date())
                            .build();
                }
        ).toList();

        transactionDetailService.createBulk(transactionDetails);
        transaction.setTransactionDetail(transactionDetails);

        List<OrderedHistory> orderedHistories = transactionDetails.stream().map(
                transactionDetail -> OrderedHistory.builder()
                        .customer(customer)
                        .merchant(transactionDetail.getProduct().getMerchant())
                        .product(transactionDetail.getProduct())
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .build()
        ).toList();

        orderedHistoryService.createBulk(orderedHistories);

        GetRewardResponse rewardResponse = rewardService.getRewardRules("purchase", totalPrice.get());

        orderedRewardService.updatedPoints(customer, rewardResponse);

        List<TransactionDetailResponse> transactionDetailResponses = transaction.getTransactionDetail().stream().map(
                transactionDetail -> TransactionDetailResponse.builder()
                        .productName(transactionDetail.getProduct().getProductName())
                        .price(transactionDetail.getProduct().getPrice())
                        .quantity(transactionDetail.getQuantity())
                        .build()
        ).toList();

        return TransactionResponse.builder()
                .transactionId(transaction.getId())
                .username(transaction.getCustomer().getUserAccount().getUsername())
                .transactionDate(transaction.getTransactionDate())
                .transactionDetails(transactionDetailResponses)
                .totalQuantity(totalQuantity.get())
                .totalPayment(totalPrice.get())
                .rewardPoints(rewardResponse.getRewardValue())
                .build();
    }
}
