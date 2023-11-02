package com.example.cellphones.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailDto {
    private Long id;
    private OrderDto order;
    private ProductDto product;
    private int quantity;
}
