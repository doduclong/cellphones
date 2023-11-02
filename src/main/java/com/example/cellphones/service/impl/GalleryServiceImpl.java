package com.example.cellphones.service.impl;

import com.example.cellphones.dto.GalleryDto;
import com.example.cellphones.repository.GalleryRepository;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {
    private final GalleryRepository galleryRepo;
    @Override
    public ResponseObject<List<GalleryDto>> uploadImage(MultipartFile files) {
//        try{
//            byte[] image = Base64.encodeBase64(files.getBytes());
//            String result = new String(image);
//            System.out.println(result);
//
//            ResponseObject<GalleryDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
//            try {
//                Gallery gallery = Gallery.builder()
//                        .image(result)
//                        .build();
//                gallery = this.galleryRepo.save(gallery);
//                res.setData(GalleryMapper.responseGalleryDtoFromModel(gallery));
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            return res;
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        return null;


    }
}
