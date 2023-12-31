package com.example.cellphones.repository;

import com.example.cellphones.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderDetail, Long> {
}
