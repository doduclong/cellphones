package com.example.cellphones.controller;

import com.example.cellphones.dto.OrderDto;
import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.dto.request.order.CreateOrderReq;
import com.example.cellphones.dto.request.product.SearchProductReq;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderReq req) {
        ResponseObject<OrderDto> res = orderService.createOrder(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/search")
    public ResponseEntity<?> searchOrder(@RequestBody SearchProductReq req) {
        ResponseObject<List<OrderDto>> res = orderService.searchOrder(req.getContains());
        return ResponseEntity.ok(res);
    }
}
