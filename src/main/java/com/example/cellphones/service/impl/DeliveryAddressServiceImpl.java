package com.example.cellphones.service.impl;

import com.example.cellphones.dto.CategoryDto;
import com.example.cellphones.dto.DeliveryAddressDto;
import com.example.cellphones.dto.ProductDto;
import com.example.cellphones.dto.request.deliveryAddress.CreateDeliveryAddressReq;
import com.example.cellphones.dto.request.deliveryAddress.UpdateDeliveryAddressReq;
import com.example.cellphones.repository.DeliveryAddressRepository;
import com.example.cellphones.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DeliveryAddressServiceImpl implements com.example.cellphones.service.DeliveryAddressService {
    private final DeliveryAddressRepository deliveryAddressRepo;
    @Override
    public ResponseObject<DeliveryAddressDto> createDeliveryAddress(CreateDeliveryAddressReq req) {
        return null;
    }

    @Override
    public boolean deleteDeliveryAddress(Long id) {
        try {
            this.deliveryAddressRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ResponseObject<List<DeliveryAddressDto>> getDeliveryAddresses(String userId) {
        return null;
    }

    @Override
    public ResponseObject<DeliveryAddressDto> updateDeliveryAddress(UpdateDeliveryAddressReq req) {
        return null;
    }
}
