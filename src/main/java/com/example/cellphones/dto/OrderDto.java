package com.example.cellphones.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {
    private Long id;
    //private List<String> listCartProduct;
    private long total;
    private String payment;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String timeOrder;
}
