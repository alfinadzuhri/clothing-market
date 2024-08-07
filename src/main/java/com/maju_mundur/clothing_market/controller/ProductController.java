package com.maju_mundur.clothing_market.controller;

import com.maju_mundur.clothing_market.constant.APIUrl;
import com.maju_mundur.clothing_market.constant.ResponseMessage;
import com.maju_mundur.clothing_market.dto.request.NewProductRequest;
import com.maju_mundur.clothing_market.dto.request.SearchRequest;
import com.maju_mundur.clothing_market.dto.request.UpdateProductRequest;
import com.maju_mundur.clothing_market.dto.response.CommonResponse;
import com.maju_mundur.clothing_market.dto.response.PagingResponse;
import com.maju_mundur.clothing_market.dto.response.ProductResponse;
import com.maju_mundur.clothing_market.entity.Product;
import com.maju_mundur.clothing_market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.PRODUCT_API)
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'MERCHANT')")
    @PostMapping
    public ResponseEntity<CommonResponse<ProductResponse>> createNewProduct(@RequestBody NewProductRequest newProductRequest) {
        ProductResponse newProduct = productService.create(newProductRequest);
        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newProduct)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<ProductResponse>>> getAllProduct(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "vendorName") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        SearchRequest request = SearchRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();

        Page<ProductResponse> products = productService.getAllProduct(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .page(products.getPageable().getPageNumber())
                .size(products.getPageable().getPageSize())
                .hasNext(products.hasNext())
                .hasPrevious(products.hasPrevious())
                .build();

        CommonResponse<List<ProductResponse>> response = CommonResponse.<List<ProductResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(products.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'MERCHANT')")
    @PutMapping
    public ResponseEntity<CommonResponse<?>> updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        ProductResponse productResponse = productService.update(updateProductRequest);
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(productResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'MERCHANT')")
    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteProduct(@PathVariable String id) {
        productService.delete(id);
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }

}
