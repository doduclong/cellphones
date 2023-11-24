package com.example.cellphones.repository;

import com.example.cellphones.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o" +
            " WHERE(:contains is null or (o.receiverName like %:contains%) " +
            "or (o.receiverPhone like %:contains%) or (o.receiverAddress like %:contains%))")
    List<Order> searchOrder(@Param("contains") String contains);

    @Query("SELECT o FROM Order o" +
            " WHERE o.user.id = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);
}
