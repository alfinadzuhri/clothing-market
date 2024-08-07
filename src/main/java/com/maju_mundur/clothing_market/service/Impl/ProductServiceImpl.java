package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.dto.request.InventoryRequest;
import com.maju_mundur.clothing_market.dto.response.ProductResponse;
import com.maju_mundur.clothing_market.entity.Product;
import com.maju_mundur.clothing_market.repository.ProductRepository;
import com.maju_mundur.clothing_market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public ProductResponse parseToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .build();
    }
}
