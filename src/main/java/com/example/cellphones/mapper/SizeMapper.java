package com.example.cellphones.mapper;

import com.example.cellphones.dto.CategoryDto;
import com.example.cellphones.dto.SizeDto;
import com.example.cellphones.model.Category;
import com.example.cellphones.model.Size;

public class SizeMapper {
    public static SizeDto responseSizeDtoFromModel(Size size){
        return SizeDto.builder()
                .id(size.getId())
                .size(size.getSize())
                .quantity(size.getQuantity())
                .build();
    }
}