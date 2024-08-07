package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.dto.request.NewProductRequest;
import com.maju_mundur.clothing_market.dto.request.SearchRequest;
import com.maju_mundur.clothing_market.dto.request.UpdateProductRequest;
import com.maju_mundur.clothing_market.dto.response.ProductResponse;
import com.maju_mundur.clothing_market.entity.Merchant;
import com.maju_mundur.clothing_market.entity.Product;
import com.maju_mundur.clothing_market.repository.ProductRepository;
import com.maju_mundur.clothing_market.service.MerchantService;
import com.maju_mundur.clothing_market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MerchantService merchantService;

    @Override
    public ProductResponse create(NewProductRequest newProductRequest) {

        Merchant merchant =  merchantService.getByUsername(newProductRequest.getMerchantUsername());

        ProductResponse productResponse = parseToProductResponse(productRepository.saveAndFlush(
          Product.builder()
                  .merchant(merchant)
                  .productName(newProductRequest.getProductName())
                  .productDescription(newProductRequest.getProductDescription())
                  .price(newProductRequest.getPrice())
                  .stocks(newProductRequest.getStocks())
                  .createdAt(new Date())
                  .updatedAt(new Date())
                  .build()
        ));

        productResponse.setMerchantUsername(merchant.getUserAccount().getUsername());

        return productResponse;
    }

    @Override
    public Page<ProductResponse> getAllProduct(SearchRequest searchRequest) {
        if (searchRequest.getPage() <= 0){
            searchRequest.setPage(1);
        }

        Pageable pageable = PageRequest.of((searchRequest.getPage() -1), searchRequest.getSize());

        List<ProductResponse> productResponses = productRepository.findAll().stream().map(
                product -> {
                    ProductResponse productResponse = parseToProductResponse(product);
                    productResponse.setMerchantUsername(product.getMerchant().getUserAccount().getUsername());
                    return productResponse;
                }
        ).toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), productResponses.size());

        List<ProductResponse> pageContent = productResponses.subList(start, end);

        return new PageImpl<>(pageContent, pageable, productResponses.size());
    }

    @Override
    public Product getById(String productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("product not found")
        );
    }

    @Override
    public ProductResponse update(UpdateProductRequest updateProductRequest) {

        Product product = getById(updateProductRequest.getProductId());

        if (updateProductRequest.getProductName() != null){
            product.setProductName(updateProductRequest.getProductName());
        }

        if (updateProductRequest.getProductDescription() != null){
            product.setProductDescription(updateProductRequest.getProductDescription());
        }

        if (updateProductRequest.getPrice() != null){
            product.setPrice(updateProductRequest.getPrice());
        }

        if (updateProductRequest.getStocks() != null){
            product.setStocks(updateProductRequest.getStocks());
        }

        product.setUpdatedAt(new Date());

        ProductResponse productResponse = parseToProductResponse(productRepository.saveAndFlush(product));
        productResponse.setMerchantUsername(product.getMerchant().getUserAccount().getUsername());

        return productResponse;
    }

    @Override
    public void delete (String productId) {
        Product product = getById(productId);
        productRepository.delete(product);
    }

    @Override
    public ProductResponse parseToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .price(product.getPrice())
                .stocks(product.getStocks())
                .build();
    }
}
