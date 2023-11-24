package com.example.cellphones.service.impl;

import com.example.cellphones.dto.CartDetailDto;
import com.example.cellphones.dto.CartDto;
import com.example.cellphones.dto.request.cart.UpdateCartDetailReq;
import com.example.cellphones.dto.request.cart.UpdateQuantityCartDetailReq;
import com.example.cellphones.exception.CartDetailNotFoundByIdException;
import com.example.cellphones.mapper.CartDetailMapper;
import com.example.cellphones.mapper.CartMapper;
import com.example.cellphones.model.*;
import com.example.cellphones.repository.CartDetailRepository;
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

    private final CartDetailRepository cartDetailRepo;


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
            List<CartDetail> listCartDetail = cart.getCartDetails();

            boolean productExists = listCartDetail.stream()
                    .anyMatch(cartDetail -> cartDetail.getProduct().getName().equals(req.getName()));

            if (productExists) {
                // Product already exists, update it
                listCartDetail.forEach(cartDetail -> {
                    if (cartDetail.getProduct().getName().equals(req.getName())) {
                        // Update properties of existing product in CartDetail
                        cartDetail.setQuantity(cartDetail.getQuantity()+req.getQuantity());
                        // Add any other properties you want to update
                    }
                });
            } else {
                Product product = this.productRepo.findByName(req.getName());
                // Product doesn't exist, add it to the listCartDetail
                listCartDetail.add(CartDetail.builder()
                                        .cart(cart)
                                        .product(product)
                                        .quantity(req.getQuantity())
                                        .size(req.getSize())
                                        .build());
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

    @Override
    public ResponseObject<CartDetailDto> updateQuantityCartDetail(UpdateQuantityCartDetailReq req) {
        ResponseObject<CartDetailDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try{
            CartDetail cartDetail = this.cartDetailRepo.findById(req.getCartDetailId())
                    .orElseThrow(() -> new CartDetailNotFoundByIdException(req.getCartDetailId()));
            cartDetail.setQuantity(req.getQuantity());
            this.cartDetailRepo.save(cartDetail);
            res.setData(CartDetailMapper.responseCartDetailDtoFromModel(cartDetail));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return res;
    }
}
