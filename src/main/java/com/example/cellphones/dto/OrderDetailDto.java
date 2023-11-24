package com.example.cellphones.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailDto {
    private Long id;
    private String productName;
    private List<GalleryDto> galleries;
    private int quantity;
    private String size;
}
