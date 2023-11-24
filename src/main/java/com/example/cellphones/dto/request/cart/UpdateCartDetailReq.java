package com.example.cellphones.dto.request.cart;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateCartDetailReq {
    private String name;
    private int quantity;
    private String size;
}
