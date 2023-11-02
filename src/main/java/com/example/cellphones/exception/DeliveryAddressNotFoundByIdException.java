package com.example.cellphones.exception;

public class DeliveryAddressNotFoundByIdException extends RuntimeException{
    public DeliveryAddressNotFoundByIdException(Long id) {
        super("Delivery address not found by " + id);
    }
}
