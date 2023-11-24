package com.example.cellphones.controller;

import com.example.cellphones.dto.OrderDto;
import com.example.cellphones.dto.request.order.CreateOrderReq;
import com.example.cellphones.dto.request.product.SearchProductReq;
import com.example.cellphones.model.User;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getProductList() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<List<OrderDto>> res = orderService.getOrderOfUser(currentUser.getId());
        return ResponseEntity.ok(res);
    }
    @PostMapping(path = "/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderReq req) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<OrderDto> res = orderService.createOrder(req, currentUser.getId());
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/search")
    public ResponseEntity<?> searchOrder(@RequestBody SearchProductReq req) {
        ResponseObject<List<OrderDto>> res = orderService.searchOrder(req.getContains());
        return ResponseEntity.ok(res);
    }
}
