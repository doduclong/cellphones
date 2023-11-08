package com.example.cellphones.service.impl;

import com.example.cellphones.dto.OrderDto;
import com.example.cellphones.dto.request.order.CreateOrderReq;
import com.example.cellphones.dto.request.order.OrderDetailReq;
import com.example.cellphones.dto.request.order.UpdateOrderStatusReq;
import com.example.cellphones.exception.UserNotFoundByIdException;
import com.example.cellphones.mapper.OrderMapper;
import com.example.cellphones.model.Order;
import com.example.cellphones.model.OrderDetail;
import com.example.cellphones.model.Product;
import com.example.cellphones.model.User;
import com.example.cellphones.repository.OrderRepository;
import com.example.cellphones.repository.ProductRepository;
import com.example.cellphones.repository.UserRepository;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {
    final private OrderRepository orderRepo;

    final private ProductRepository productRepo;

    final private UserRepository userRepo;


    @Override
    public ResponseObject<OrderDto> createOrder(CreateOrderReq request, Long userId) {
        ResponseObject<OrderDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date now = new Date();
            int tmpTotal = 0;
            User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundByIdException(userId));
            List<OrderDetailReq> listOrderDetailReq = request.getListOrderProduct();
            List<OrderDetail> listOrderDetail = new ArrayList<>();


            Order order = Order.builder()
                    .user(user)
                    .payment(request.getPayment())
                    .receiverName(request.getReceiverName())
                    .receiverPhone(request.getReceiverPhone())
                    .receiverAddress(request.getReceiverAddress())
                    .timeOrder(formatter.format(now))
                    .build();

            for (OrderDetailReq orderDetailReq : listOrderDetailReq) {
                Product product = productRepo.findByName(orderDetailReq.getName());
                OrderDetail orderDetail = OrderDetail.builder()
                        .product(product)
                        .quantity(orderDetailReq.getQuantity())
                        .order(order)
                        .build();
                listOrderDetail.add(orderDetail);
                tmpTotal += product.getPrice() * orderDetailReq.getQuantity();
            }
            order.setTotal(tmpTotal);
            order.setListOrderDetail(listOrderDetail);
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
        return null;
    }
}
