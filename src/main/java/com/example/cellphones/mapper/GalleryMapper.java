package com.example.cellphones.mapper;
import com.example.cellphones.dto.GalleryDto;
import com.example.cellphones.model.Gallery;

public class GalleryMapper {
    public static GalleryDto responseGalleryDtoFromModel(Gallery gallery){
        return GalleryDto.builder()
                .id(gallery.getId())
                .image(gallery.getImage())
                .build();
    }
}
