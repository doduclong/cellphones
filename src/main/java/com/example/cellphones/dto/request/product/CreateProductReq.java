package com.example.cellphones.dto.request.product;

import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductReq {
    private String size;
    private int quantity;
}
