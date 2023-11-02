package com.example.cellphones.service;

import com.example.cellphones.dto.DeliveryAddressDto;
import com.example.cellphones.dto.request.deliveryAddress.CreateDeliveryAddressReq;
import com.example.cellphones.dto.request.deliveryAddress.UpdateDeliveryAddressReq;
import com.example.cellphones.model.User;
import com.example.cellphones.response.ResponseObject;

import java.util.List;

public interface DeliveryAddressService {
    ResponseObject<DeliveryAddressDto> createDeliveryAddress(CreateDeliveryAddressReq req, User user);

    boolean deleteDeliveryAddress(Long id);

    ResponseObject<List<DeliveryAddressDto>> getDeliveryAddresses(Long userId);

    ResponseObject<DeliveryAddressDto> updateDeliveryAddress(UpdateDeliveryAddressReq req, Long id);
}
