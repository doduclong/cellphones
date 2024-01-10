package com.example.cellphones.controller;
import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.dto.request.product.UpdateProductReq;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        boolean deleted = productService.deleteProduct(Long.parseLong(id));
        if (deleted){
            return ResponseEntity.ok(new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL, "Deleted product successfully !"));
        }
        return ResponseEntity.ok(new ResponseObject<>(false, ResponseStatus.UNHANDLED_ERROR, "Deleted product failed !"));
    }

    @PostMapping(path = "/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createProduct(
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam("name") String name,
            @RequestParam("describe") String describe,
            @RequestParam("price") int price,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("classification") String classification,
            @RequestParam("sizes") String reqs
    ) {
        ResponseObject<ProductDto> res = productService.createProduct(name, describe, price, categoryId, files,classification, reqs);
        return ResponseEntity.ok(res);
    }

    @PutMapping(path = "/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductReq req) {
        ResponseObject<ProductDto> res = productService.updateProduct(req);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        ResponseObject<ProductDto> res = productService.getProductById(Long.parseLong(id));
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/category/{id}")
    public ResponseEntity<?> getProductByCategoryId(@PathVariable String id) {
        ResponseObject<List<ProductDto>> res = productService.searchProductByCategory(Long.parseLong(id));
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getProductList() {
        ResponseObject<List<ProductDto>> res = productService.getProductList();
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchProduct(@RequestParam String searchText) {
        ResponseObject<List<ProductDto>> res = productService.searchProduct(searchText);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/search/keywords")
    public ResponseEntity<?> searchProductByKeywords(@RequestParam List<String> keywords) {
        ResponseObject<List<ProductDto>> res = productService.searchProductByKeywords(keywords);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/search/under-price")
    public ResponseEntity<?> searchProductUnderPrice(@RequestParam String price) {
        ResponseObject<List<ProductDto>> res = productService.searchUnderPrice(Integer.parseInt(price));
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/search/over-price")
    public ResponseEntity<?> searchProductOverPrice(@RequestParam String price) {
        ResponseObject<List<ProductDto>> res = productService.searchOverPrice(Integer.parseInt(price));
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/newest")
    public ResponseEntity<?> newestProduct() {
        ResponseObject<ProductDto> res = productService.newestProduct();
        return ResponseEntity.ok(res);
    }

}
