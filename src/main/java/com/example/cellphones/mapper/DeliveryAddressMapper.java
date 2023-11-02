package com.example.cellphones.mapper;
import com.example.cellphones.dto.DeliveryAddressDto;
import com.example.cellphones.model.DeliveryAddress;

public class DeliveryAddressMapper {
    public static DeliveryAddressDto responseDeliveryAddressDtoFromModel(DeliveryAddress deliveryAddress){
        return DeliveryAddressDto.builder()
                .id(deliveryAddress.getId())
                .address(deliveryAddress.getAddress())
                .fullName(deliveryAddress.getFullName())
                .phoneNumber(deliveryAddress.getPhoneNumber())
                .build();
    }
}
