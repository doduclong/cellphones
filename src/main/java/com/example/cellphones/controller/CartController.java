package com.example.cellphones.controller;

import com.example.cellphones.dto.CartDetailDto;
import com.example.cellphones.dto.CartDto;
import com.example.cellphones.dto.request.cart.UpdateCartDetailReq;
import com.example.cellphones.dto.request.cart.UpdateQuantityCartDetailReq;
import com.example.cellphones.model.User;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping(path = "/")
    public ResponseEntity<?> getCart() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<CartDto> res = cartService.getCart(currentUser.getId());
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/add-product")
    public ResponseEntity<?> updateCartDetail(@RequestBody UpdateCartDetailReq req) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<CartDto> res = cartService.addProductToCart(req, currentUser.getId());
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/update-quantity")
    public ResponseEntity<?> updateQuantityCartDetail(@RequestBody UpdateQuantityCartDetailReq req) {
        ResponseObject<CartDetailDto> res = cartService.updateQuantityCartDetail(req);
        return ResponseEntity.ok(res);
    }
}
