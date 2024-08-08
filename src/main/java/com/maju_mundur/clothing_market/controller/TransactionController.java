package com.maju_mundur.clothing_market.controller;

import com.maju_mundur.clothing_market.constant.APIUrl;
import com.maju_mundur.clothing_market.dto.request.TransactionRequest;
import com.maju_mundur.clothing_market.dto.response.CommonResponse;
import com.maju_mundur.clothing_market.dto.response.TransactionDetailResponse;
import com.maju_mundur.clothing_market.dto.response.TransactionResponse;
import com.maju_mundur.clothing_market.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionController {

    private  final TransactionService transactionService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<CommonResponse<TransactionResponse>> createNewTransaction(@RequestBody TransactionRequest transactionRequest){

        TransactionResponse newTransaction = transactionService.create(transactionRequest);

        CommonResponse<TransactionResponse> response = CommonResponse.<TransactionResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully Transaction")
                .data(newTransaction)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
