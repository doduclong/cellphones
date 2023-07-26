package com.example.cellphones.dto.request.order;

import com.example.cellphones.model.OrderProduct;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateOrderReq {
    private List<OrderProductReq> listOrderProduct;
    //private int total;
    private String payment;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
}
