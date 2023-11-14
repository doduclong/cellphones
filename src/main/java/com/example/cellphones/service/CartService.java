package com.example.cellphones.service;

import com.example.cellphones.dto.CartDetailDto;
import com.example.cellphones.dto.CartDto;
import com.example.cellphones.dto.request.cart.UpdateCartDetailReq;
import com.example.cellphones.dto.request.cart.UpdateQuantityCartDetailReq;
import com.example.cellphones.response.ResponseObject;

public interface CartService {
    ResponseObject<CartDto> getCart(Long userId);

    ResponseObject<CartDto> addProductToCart(UpdateCartDetailReq req, Long userId);

    ResponseObject<CartDetailDto> updateQuantityCartDetail(UpdateQuantityCartDetailReq req);
}
