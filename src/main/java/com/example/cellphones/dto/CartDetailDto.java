package com.example.cellphones.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartDetailDto {
    private Long id;
    private String productName;
    private int quantity;
}
