package com.example.cellphones.service;



import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.dto.request.product.CreateProductReq;
import com.example.cellphones.dto.request.product.UpdateProductReq;
import com.example.cellphones.response.ResponseObject;

import java.util.List;

public interface ProductService {
    ResponseObject<List<ProductDto>> getProductList();
    List<ProductDto> getProducts();
    ResponseObject<ProductDto> createProduct(CreateProductReq request);
    ResponseObject<ProductDto> updateProduct(UpdateProductReq request);
    boolean deleteProduct(Long id);

    ResponseObject<List<ProductDto>> searchProduct(String searchText);

    ResponseObject<ProductDto> getProductById(Long id);

}
