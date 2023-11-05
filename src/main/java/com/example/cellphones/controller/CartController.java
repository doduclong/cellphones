package com.example.cellphones.controller;

import com.example.cellphones.dto.CartDto;
import com.example.cellphones.model.User;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping(path = "/")
    public ResponseEntity<?> getDeliveryAddresses() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<CartDto> res = cartService.getCart(currentUser.getId());
        return ResponseEntity.ok(res);
    }
}
