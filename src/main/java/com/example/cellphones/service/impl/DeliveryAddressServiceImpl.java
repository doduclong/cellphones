package com.example.cellphones.service.impl;
import com.example.cellphones.dto.DeliveryAddressDto;
import com.example.cellphones.dto.request.deliveryAddress.CreateDeliveryAddressReq;
import com.example.cellphones.dto.request.deliveryAddress.UpdateDeliveryAddressReq;
import com.example.cellphones.exception.DeliveryAddressNotFoundByIdException;
import com.example.cellphones.exception.UserNotFoundByIdException;
import com.example.cellphones.mapper.DeliveryAddressMapper;
import com.example.cellphones.model.DeliveryAddress;
import com.example.cellphones.model.User;
import com.example.cellphones.repository.DeliveryAddressRepository;
import com.example.cellphones.repository.UserRepository;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryAddressServiceImpl implements com.example.cellphones.service.DeliveryAddressService {
    private final DeliveryAddressRepository deliveryAddressRepo;
    private final UserRepository userRepo;
    @Override
    public ResponseObject<DeliveryAddressDto> createDeliveryAddress(CreateDeliveryAddressReq request, User user) {
        ResponseObject<DeliveryAddressDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {
            DeliveryAddress deliveryAddress = DeliveryAddress.builder()
                    .user(user)
                    .fullName(request.getFullName())
                    .address(request.getAddress())
                    .phoneNumber(request.getPhoneNumber())
                    .build();
            deliveryAddress = this.deliveryAddressRepo.save(deliveryAddress);
            res.setData(DeliveryAddressMapper.responseDeliveryAddressDtoFromModel(deliveryAddress));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
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
    public ResponseObject<List<DeliveryAddressDto>> getDeliveryAddresses(Long userId) {
        ResponseObject<List<DeliveryAddressDto>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundByIdException(userId));
        List<DeliveryAddress> deliveryAddresses = this.deliveryAddressRepo.findDeliveryAddressByUser(user);
        res.setData(deliveryAddresses.stream().map(DeliveryAddressMapper::responseDeliveryAddressDtoFromModel).collect(Collectors.toList()));
        return res;
    }

    @Override
    public ResponseObject<DeliveryAddressDto> updateDeliveryAddress(UpdateDeliveryAddressReq request, Long id) {
        ResponseObject<DeliveryAddressDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        DeliveryAddress oldDeliveryAddress = this.deliveryAddressRepo.findById(id).orElseThrow(() -> new DeliveryAddressNotFoundByIdException(id));
        oldDeliveryAddress.setAddress(request.getAddress());
        oldDeliveryAddress.setFullName(request.getFullName());
        oldDeliveryAddress.setPhoneNumber(request.getPhoneNumber());
        oldDeliveryAddress = this.deliveryAddressRepo.saveAndFlush(oldDeliveryAddress);
        res.setData(DeliveryAddressMapper.responseDeliveryAddressDtoFromModel(oldDeliveryAddress));
        return res;
    }
}
