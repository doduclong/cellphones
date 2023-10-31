package com.example.cellphones.dto;

import lombok.*;


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
    private String type;
    private CategoryDto category;
//    private List<GalleryDto> galleries;
}
