package com.example.cellphones.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeliveryAddressDto {
    private Long id;
    private String address;
    private String fullName;
    private String phoneNumber;
}
