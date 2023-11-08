package com.example.cellphones.dto;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String describe;
    private int price;
    private List<GalleryDto> galleries;
    private CategoryDto category;
}
