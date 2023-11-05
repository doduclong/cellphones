package com.example.cellphones.service.impl;

import com.example.cellphones.dto.CartDto;
import com.example.cellphones.mapper.CartMapper;
import com.example.cellphones.model.Cart;
import com.example.cellphones.repository.CartRepository;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepo;

    @Override
    public ResponseObject<CartDto> getCart(Long userId) {
        ResponseObject<CartDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Cart cart = this.cartRepo.findCartByUserId(userId);
        res.setData(CartMapper.responseCartDtoFromModel(cart));
        return res;
    }
}
