package com.maju_mundur.clothing_market.controller;

import com.maju_mundur.clothing_market.constant.APIUrl;
import com.maju_mundur.clothing_market.constant.ResponseMessage;
import com.maju_mundur.clothing_market.dto.request.InventoryRequest;
import com.maju_mundur.clothing_market.dto.response.CommonResponse;
import com.maju_mundur.clothing_market.dto.response.InventoryResponse;
import com.maju_mundur.clothing_market.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.INVENTORY_API)
public class InventoryController {

    private final InventoryService inventoryService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'MERCHANT')")
    @PostMapping
    public ResponseEntity<CommonResponse<InventoryResponse>> createNewVendor(@RequestBody InventoryRequest inventoryRequest) {
        InventoryResponse newInventory = inventoryService.create(inventoryRequest);
        CommonResponse<InventoryResponse> response = CommonResponse.<InventoryResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newInventory)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
