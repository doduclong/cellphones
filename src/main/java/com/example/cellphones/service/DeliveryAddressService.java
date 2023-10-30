package com.example.cellphones.service;

import com.example.cellphones.dto.CategoryDto;
import com.example.cellphones.dto.DeliveryAddressDto;
import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.dto.request.deliveryAddress.CreateDeliveryAddressReq;
import com.example.cellphones.dto.request.deliveryAddress.UpdateDeliveryAddressReq;
import com.example.cellphones.response.ResponseObject;

import java.util.List;

public interface DeliveryAddressService {
    ResponseObject<DeliveryAddressDto> createDeliveryAddress(CreateDeliveryAddressReq req);

    boolean deleteDeliveryAddress(Long id);

    ResponseObject<List<DeliveryAddressDto>> getDeliveryAddresses(String userId);

    ResponseObject<DeliveryAddressDto> updateDeliveryAddress(UpdateDeliveryAddressReq req);
}
