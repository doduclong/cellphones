package com.example.cellphones.mapper;
import com.example.cellphones.dto.OrderDetailDto;
import com.example.cellphones.model.OrderDetail;

import java.util.stream.Collectors;

public class OrderDetailMapper {
    public static OrderDetailDto responseOrderDetailDtoFromModel(OrderDetail orderDetail){
        return OrderDetailDto.builder()
                .id(orderDetail.getId())
                .productName(orderDetail.getProduct().getName())
                .price(orderDetail.getProduct().getPrice())
                .galleries(orderDetail.getProduct().getGalleries().stream().map(GalleryMapper::responseGalleryDtoFromModel).collect(Collectors.toList()))
                .quantity(orderDetail.getQuantity())
                .size(orderDetail.getSize())
                .build();
    }
}
