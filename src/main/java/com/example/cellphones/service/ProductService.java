package com.example.cellphones.service;



import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.dto.request.product.UpdateProductReq;
import com.example.cellphones.response.ResponseObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ResponseObject<List<ProductDto>> getProductList();
    ResponseObject<ProductDto> createProduct(String name, String describe, int price, Long categoryId, List<MultipartFile> files);
    ResponseObject<ProductDto> updateProduct(UpdateProductReq request);
    boolean deleteProduct(Long id);

    ResponseObject<List<ProductDto>> searchProduct(String searchText);

    ResponseObject<ProductDto> getProductById(Long id);

}
