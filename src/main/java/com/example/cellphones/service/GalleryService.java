package com.example.cellphones.service;

import com.example.cellphones.dto.GalleryDto;
import com.example.cellphones.response.ResponseObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {
    ResponseObject<List<GalleryDto>> uploadImage(MultipartFile files);
}
