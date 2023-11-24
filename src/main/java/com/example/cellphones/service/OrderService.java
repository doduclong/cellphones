package com.example.cellphones.service;

import com.example.cellphones.dto.OrderDto;
import com.example.cellphones.dto.request.order.CreateOrderReq;
import com.example.cellphones.dto.request.order.UpdateOrderStatusReq;
import com.example.cellphones.response.ResponseObject;

import java.util.List;

public interface OrderService {
    ResponseObject<List<OrderDto>> getOrderOfUser(Long userId);

    ResponseObject<OrderDto> createOrder(CreateOrderReq request, Long userId);

    ResponseObject<List<OrderDto>> searchOrder(String contains);

    ResponseObject<OrderDto> updateOrderStatus(UpdateOrderStatusReq request);
}
