package com.example.cellphones.controller;
import com.example.cellphones.dto.GalleryDto;
import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.dto.request.product.CreateProductReq;
import com.example.cellphones.dto.request.product.SearchProductReq;
import com.example.cellphones.dto.request.product.UpdateProductReq;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.GalleryService;
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

    private final GalleryService galleryService;

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
    public ResponseEntity<?> createProduct(@RequestBody CreateProductReq req) {
        ResponseObject<ProductDto> res = productService.createProduct(req);
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

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getProductList() {
        ResponseObject<List<ProductDto>> res = productService.getProductList();
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/search")
    public ResponseEntity<?> searchProduct(@RequestBody SearchProductReq req) {
        ResponseObject<List<ProductDto>> res = productService.searchProduct(req.getContains());
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile files) {
        ResponseObject<List<GalleryDto>> res = galleryService.uploadImage(files);
        return ResponseEntity.ok(res);
    }
}
