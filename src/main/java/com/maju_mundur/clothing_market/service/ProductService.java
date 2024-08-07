package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.dto.request.NewProductRequest;
import com.maju_mundur.clothing_market.dto.request.SearchRequest;
import com.maju_mundur.clothing_market.dto.request.UpdateProductRequest;
import com.maju_mundur.clothing_market.dto.response.ProductResponse;
import com.maju_mundur.clothing_market.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductResponse create (NewProductRequest newProductRequest);
    Page<ProductResponse> getAllProduct (SearchRequest searchRequest);
    Product getById(String productId);
    ProductResponse update (UpdateProductRequest updateProductRequest);
    void delete (String productId);
    ProductResponse parseToProductResponse(Product product);
}
