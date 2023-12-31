package com.example.cellphones.controller;

import com.example.cellphones.dto.OrderDto;
import com.example.cellphones.dto.request.order.CreateOrderReq;
import com.example.cellphones.dto.request.order.UpdateOrderStatusReq;
import com.example.cellphones.dto.request.product.SearchProductReq;
import com.example.cellphones.model.User;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping(path = "/user")
    public ResponseEntity<?> getOrderOfUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<List<OrderDto>> res = orderService.getOrderOfUser(currentUser.getId());
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> getOrders() {
        ResponseObject<List<OrderDto>> res = orderService.getOrders();
        return ResponseEntity.ok(res);
    }
    @PostMapping(path = "/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderReq req) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<OrderDto> res = orderService.createOrder(req, currentUser.getId());
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/update-status")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateStatusOrder(@RequestBody UpdateOrderStatusReq req) {
        ResponseObject<OrderDto> res = orderService.updateOrderStatus(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/search")
    public ResponseEntity<?> searchOrder(@RequestBody SearchProductReq req) {
        ResponseObject<List<OrderDto>> res = orderService.searchOrder(req.getContains());
        return ResponseEntity.ok(res);
    }
}
