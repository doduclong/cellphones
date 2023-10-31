package com.example.cellphones.controller;

import com.example.cellphones.dto.DeliveryAddressDto;
import com.example.cellphones.dto.request.deliveryAddress.CreateDeliveryAddressReq;
import com.example.cellphones.dto.request.deliveryAddress.UpdateDeliveryAddressReq;
import com.example.cellphones.model.User;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/delivery-address")
@RequiredArgsConstructor
public class DeliveryAddressController {
    private final DeliveryAddressService deliveryAddressService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getDeliveryAddresses() {
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<List<DeliveryAddressDto>> res = deliveryAddressService.getDeliveryAddresses(currentUser.getId());
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createDeliveryAddress(@RequestBody CreateDeliveryAddressReq req) {
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseObject<DeliveryAddressDto> res = deliveryAddressService.createDeliveryAddress(req, currentUser);
        return ResponseEntity.ok(res);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody UpdateDeliveryAddressReq req) {
        ResponseObject<DeliveryAddressDto> res = deliveryAddressService.updateDeliveryAddress(req, Long.parseLong(id));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        boolean deleted = deliveryAddressService.deleteDeliveryAddress(Long.parseLong(id));
        if (deleted){
            return ResponseEntity.ok(new ResponseObject<>(true, com.example.cellphones.response.ResponseStatus.DO_SERVICE_SUCCESSFUL, "Deleted delivery address successfully !"));
        }
        return ResponseEntity.ok(new ResponseObject<>(false, ResponseStatus.UNHANDLED_ERROR, "Deleted delivery address failed !"));
    }

}
