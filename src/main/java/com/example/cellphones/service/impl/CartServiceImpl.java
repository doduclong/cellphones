package com.example.cellphones.service.impl;

import com.example.cellphones.dto.CartDto;
import com.example.cellphones.dto.request.cart.CartDetailReq;
import com.example.cellphones.dto.request.cart.UpdateCartDetailReq;
import com.example.cellphones.mapper.CartMapper;
import com.example.cellphones.model.*;
import com.example.cellphones.repository.CartRepository;
import com.example.cellphones.repository.ProductRepository;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepo;

    private final ProductRepository productRepo;

    @Override
    public ResponseObject<CartDto> getCart(Long userId) {
        ResponseObject<CartDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Cart cart = this.cartRepo.findCartByUserId(userId);
        res.setData(CartMapper.responseCartDtoFromModel(cart));
        return res;
    }

    @Override
    public ResponseObject<CartDto> addProductToCart(UpdateCartDetailReq req, Long userId) {
        ResponseObject<CartDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {
            Cart cart = cartRepo.findCartByUserId(userId);
            List<CartDetailReq> listCartDetailReq = req.getListCartDetail();
            List<CartDetail> listCartDetail = cart.getCartDetails();

            for (CartDetailReq cartDetailReq : listCartDetailReq) {
                Product product = productRepo.findByName(cartDetailReq.getName());
                CartDetail cartDetail = CartDetail.builder()
                        .product(product)
                        .quantity(cartDetailReq.getQuantity())
                        .cart(cart)
                        .build();
                listCartDetail.add(cartDetail);
                //tmpTotal += product.getPrice() * cartDetailReq.getQuantity();
            }
            //cart.setTotal(tmpTotal);
            cart.setCartDetails(listCartDetail);
            cart = this.cartRepo.save(cart);
            res.setData(CartMapper.responseCartDtoFromModel(cart));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
