package com.example.cellphones.service.impl;

import com.example.cellphones.dto.OrderDto;
import com.example.cellphones.dto.request.order.CreateOrderReq;
import com.example.cellphones.dto.request.order.OrderProductReq;
import com.example.cellphones.mapper.OrderMapper;
import com.example.cellphones.mapper.ProductMapper;
import com.example.cellphones.model.Order;
import com.example.cellphones.model.OrderProduct;
import com.example.cellphones.model.Product;
import com.example.cellphones.repository.OrderProductRepository;
import com.example.cellphones.repository.OrderRepository;
import com.example.cellphones.repository.ProductRepository;
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


    @Override
    public ResponseObject<OrderDto> createOrder(CreateOrderReq request) {
        ResponseObject<OrderDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date now = new Date();
            int tmpTotal = 0;
            List<OrderProductReq> listOrderProductReq = request.getListOrderProduct();
            List<OrderProduct> listOrderProduct = new ArrayList<>();


            Order order = Order.builder()
                    .payment(request.getPayment())
                    .receiverName(request.getReceiverName())
                    .receiverPhone(request.getReceiverPhone())
                    .receiverAddress(request.getReceiverAddress())
                    .timeOrder(formatter.format(now))
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
}
