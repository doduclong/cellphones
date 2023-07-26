package com.example.cellphones.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderProductDto {
    private Long id;
    private OrderDto cart;
    private ProductDto product;
    private int quantity;
}
