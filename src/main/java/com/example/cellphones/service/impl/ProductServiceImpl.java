package com.example.cellphones.service.impl;

import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.dto.request.product.UpdateProductReq;
import com.example.cellphones.exception.CategoryNotFoundByIdException;
import com.example.cellphones.exception.ProductNotFoundByIdException;
import com.example.cellphones.mapper.ProductMapper;
import com.example.cellphones.model.Category;
import com.example.cellphones.model.Gallery;
import com.example.cellphones.model.Product;
import com.example.cellphones.repository.CategoryRepository;
import com.example.cellphones.repository.GalleryRepository;
import com.example.cellphones.repository.ProductRepository;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    private final CategoryRepository categoryRepo;

    private final GalleryRepository galleryRepo;

    @Override
    public ResponseObject<List<ProductDto>> getProductList() {
        ResponseObject<List<ProductDto>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        List<Product> country = this.productRepo.findAll();
        res.setData(country.stream().map(ProductMapper::responseProductDtoFromModel).collect(Collectors.toList()));
        return res;
    }

//    @Override
//    public List<ProductDto> getProducts() {
//        List<Product> foods = this.productRepo.findAll();
//        return foods.stream().map(ProductMapper::responseProductDtoFromModel).collect(Collectors.toList());
//    }

    @Override
    public ResponseObject<ProductDto> createProduct(String name, String describe, int price, Long categoryId, List<MultipartFile> files) {
        ResponseObject<ProductDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundByIdException(categoryId));

        List<Gallery> galleries = new ArrayList<>();

        try {
            Product product = Product.builder()
                    .name(name)
                    .describe(describe)
                    .price(price)
                    .category(category)
                    .build();

            for (MultipartFile file : files) {
                byte[] image = Base64.encodeBase64(file.getBytes());
                String result = new String(image);
                if (product.getGalleries() == null) {
                    product.setGalleries(new ArrayList<>());
                }
                product.getGalleries().add(Gallery.builder()
                        .image(result)
                        .product(product)
                        .build());
            }
            product.setGalleries(galleries);
            product = this.productRepo.save(product);
            res.setData(ProductMapper.responseProductDtoFromModel(product));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public ResponseObject<ProductDto> updateProduct(UpdateProductReq request) {
        ResponseObject<ProductDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Product oldProduct = this.productRepo.findById(request.getId())
                .orElseThrow(() -> new ProductNotFoundByIdException(request.getId()));

        oldProduct.setName(request.getName());
        oldProduct.setDescribe(request.getDescribe());
        oldProduct.setPrice(request.getPrice());
        oldProduct = this.productRepo.saveAndFlush(oldProduct);
        res.setData(ProductMapper.responseProductDtoFromModel(oldProduct));
        return res;
    }

    @Override
    public boolean deleteProduct(Long id) {
        try {
            this.productRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ResponseObject<List<ProductDto>> searchProduct(String searchText) {
        ResponseObject<List<ProductDto>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        List<Product> country = this.productRepo.searchByNameProduct(searchText);
        res.setData(country.stream().map(ProductMapper::responseProductDtoFromModel).collect(Collectors.toList()));
        return res;
    }

    @Override
    public ResponseObject<ProductDto> getProductById(Long id) {
        ResponseObject<ProductDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Product product = this.productRepo.findById(id)
                .orElseThrow(()-> new ProductNotFoundByIdException("Product not found by id ",id));
        res.setData(ProductMapper.responseProductDtoFromModel(product));
        return res;
    }

}
