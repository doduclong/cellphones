package com.example.cellphones.dto.request.cart;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateCartDetailReq {
    private List<CartDetailReq> listCartDetail;
}
