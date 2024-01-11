package com.example.cellphones.repository;

import com.example.cellphones.model.Product;
import com.example.cellphones.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    @Query("SELECT t FROM Size t WHERE (t.size=:nameSize AND t.product.id = :productId)")
    Size findByName(String nameSize, Long productId);
}
