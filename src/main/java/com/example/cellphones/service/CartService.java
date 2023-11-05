package com.example.cellphones.service;

import com.example.cellphones.dto.CartDto;
import com.example.cellphones.response.ResponseObject;

public interface CartService {
    ResponseObject<CartDto> getCart(Long userId);
}
