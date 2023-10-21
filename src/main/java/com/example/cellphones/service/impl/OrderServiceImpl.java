package com.example.cellphones.service.impl;

import com.example.cellphones.dto.OrderDto;
import com.example.cellphones.dto.request.order.CreateOrderReq;
import com.example.cellphones.dto.request.order.OrderProductReq;
import com.example.cellphones.dto.request.order.UpdateOrderStatusReq;
import com.example.cellphones.exception.OrderNotFoundByIdException;
import com.example.cellphones.exception.UserNotFoundByUsername;
import com.example.cellphones.mapper.OrderMapper;
import com.example.cellphones.model.*;
import com.example.cellphones.repository.OrderRepository;
import com.example.cellphones.repository.ProductRepository;
import com.example.cellphones.repository.UserRepository;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;

    private final ProductRepository productRepo;


    @Override
    public ResponseObject<OrderDto> createOrder(CreateOrderReq request) {
        ResponseObject<OrderDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date now = new Date();
            int tmpTotal = 0;
            List<OrderProductReq> listOrderProductReq = request.getListOrderProduct();
            List<OrderProduct> listOrderProduct = new ArrayList<>();


            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String username;
            if (principal instanceof UserDetails) {
                 username = ((UserDetails)principal).getUsername();
            } else {
                 username = principal.toString();
            }

            User currentUser = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundByUsername(username));


            Order order = Order.builder()
                    .payment(request.getPayment())
                    .receiverName(request.getReceiverName())
                    .receiverPhone(request.getReceiverPhone())
                    .receiverAddress(request.getReceiverAddress())
                    .timeOrder(formatter.format(now))
                    .user(currentUser)
                    .status(OrderStatus.NEW)
                    .build();

            for(int orderProductIndex = 0; orderProductIndex< listOrderProductReq.size(); orderProductIndex++){
                Product product = productRepo.findByName(listOrderProductReq.get(orderProductIndex).getName());
                OrderProduct orderProduct = OrderProduct.builder()
                        .product(product)
                        .quantity(listOrderProductReq.get(orderProductIndex).getQuantity())
                        .order(order)
                        .build();
                listOrderProduct.add(orderProduct);
                tmpTotal +=  product.getPrice()* listOrderProductReq.get(orderProductIndex).getQuantity();
            }
            order.setTotal(tmpTotal);
            order.setListOrderProduct(listOrderProduct);
            order = this.orderRepo.save(order);
            res.setData(OrderMapper.responseOrderDtoFromModel(order));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public ResponseObject<List<OrderDto>> searchOrder(String contains) {
        ResponseObject<List<OrderDto>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        List<Order> listOrder = this.orderRepo.searchOrder(contains);
        res.setData(listOrder.stream().map(OrderMapper::responseOrderDtoFromModel).collect(Collectors.toList()));
        return res;
    }

    @Override
    public ResponseObject<OrderDto> updateOrderStatus(UpdateOrderStatusReq request) {
        ResponseObject<OrderDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Order oldOrder = this.orderRepo.findById(request.getId())
                .orElseThrow(() -> new OrderNotFoundByIdException(request.getId()));

        oldOrder.setStatus(request.getStatus());
        oldOrder = this.orderRepo.saveAndFlush(oldOrder);
        res.setData(OrderMapper.responseOrderDtoFromModel(oldOrder));
        return res;
    }


}
