package com.example.cellphones.dto.request.product;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateProductReq {
    private Long id;
    private String name;
    private String describe;
    private int price;
    private int img;
    private String type;
}
