package com.example.cellphones.repository;

import com.example.cellphones.model.DeliveryAddress;
import com.example.cellphones.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    List<DeliveryAddress> findDeliveryAddressByUser(User user);
}
