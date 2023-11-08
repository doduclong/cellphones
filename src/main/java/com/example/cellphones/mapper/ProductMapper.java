package com.example.cellphones.mapper;

import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.model.Product;

import java.util.stream.Collectors;

public class ProductMapper {
    public ProductMapper() {
    }
    public static ProductDto responseProductDtoFromModel(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .describe(product.getDescribe())
                .price(product.getPrice())
                .galleries(product.getGalleries().stream().map(GalleryMapper::responseGalleryDtoFromModel).collect(Collectors.toList()))
                .category(CategoryMapper.responseCategoryDtoFromModel(product.getCategory()))
                .build();
    }
}
