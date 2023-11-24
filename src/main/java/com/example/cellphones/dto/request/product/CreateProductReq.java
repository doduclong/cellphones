package com.example.cellphones.dto.request.product;

import com.example.cellphones.model.Size;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductReq {
//    private String name;
//    private String describe;
//    private int price;
//    private int img;
//    private String type;

//    private List<Size> sizes;

    private String size;
    private int quantity;
}
