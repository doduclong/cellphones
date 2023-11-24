package com.example.cellphones.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SizeDto {
    private Long id;
    private String size;
    private int quantity;
}
