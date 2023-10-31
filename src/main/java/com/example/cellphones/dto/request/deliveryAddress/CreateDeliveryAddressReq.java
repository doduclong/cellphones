package com.example.cellphones.dto.request.deliveryAddress;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateDeliveryAddressReq {
    private String address;
    private String fullName;
    private String phoneNumber;
}
