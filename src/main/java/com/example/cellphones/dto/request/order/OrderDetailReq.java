package com.example.cellphones.dto.request.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailReq {
    private String name;
    private int quantity;
    private String size;
}
