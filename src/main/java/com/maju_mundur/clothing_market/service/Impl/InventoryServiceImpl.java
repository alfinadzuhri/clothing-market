package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.dto.request.InventoryRequest;
import com.maju_mundur.clothing_market.dto.response.InventoryResponse;
import com.maju_mundur.clothing_market.dto.response.MerchantResponse;
import com.maju_mundur.clothing_market.dto.response.ProductResponse;
import com.maju_mundur.clothing_market.entity.Inventory;
import com.maju_mundur.clothing_market.entity.Merchant;
import com.maju_mundur.clothing_market.entity.Product;
import com.maju_mundur.clothing_market.repository.InventoryRepository;
import com.maju_mundur.clothing_market.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductServiceImpl productService;
    private final MerchantServiceImpl merchantService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public InventoryResponse create(InventoryRequest inventoryRequest) {

        Product newProduct = productService.create(
                Product.builder()
                        .productName(inventoryRequest.getProductName())
                        .productDescription(inventoryRequest.getProductDescription())
                        .createdAt(new Date())
                        .updatedAt(new Date()).build());

        ProductResponse productResponse = productService.parseToProductResponse(newProduct);

        Merchant merchant = merchantService.getByUsername(inventoryRequest.getMerchantUsername());

        Inventory inventory = Inventory.builder()
                .product(newProduct)
                .merchant(merchant)
                .price(inventoryRequest.getPrice())
                .stocks(inventoryRequest.getStocks())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        inventoryRepository.saveAndFlush(inventory);

        return InventoryResponse.builder()
                .inventoryId(inventory.getId())
                .productResponse(productResponse)
                .merchantResponse(merchantService.parseToMerchantResponse(merchant))
                .price(inventory.getPrice())
                .stocks(inventory.getStocks())
                .createdAt(inventory.getCreatedAt())
                .build();
    }
}
