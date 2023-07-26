package com.example.cellphones.dto.request.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderProductReq {
    private String name;
    private int quantity;
}
