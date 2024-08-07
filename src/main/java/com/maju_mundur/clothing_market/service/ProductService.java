package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.dto.request.InventoryRequest;
import com.maju_mundur.clothing_market.dto.response.InventoryResponse;
import com.maju_mundur.clothing_market.dto.response.ProductResponse;
import com.maju_mundur.clothing_market.entity.Product;

public interface ProductService {
    Product create (Product product);
    ProductResponse parseToProductResponse(Product product);
}
