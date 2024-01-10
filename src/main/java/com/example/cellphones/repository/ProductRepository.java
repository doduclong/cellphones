package com.example.cellphones.repository;

import com.example.cellphones.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT e FROM Product e" +
            " WHERE(:contains is null or (e.name like %:contains%))")
    List<Product> searchByNameProduct(@Param("contains") String contains);

    @Query("SELECT t FROM Product t WHERE t.name=:name")
    Product findByName(String name);

    @Query("SELECT e FROM Product e" +
            " WHERE(:keyword is null or (e.classification = :keyword))")
    Product searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT e FROM Product e" +
            " WHERE :price >= e.price")
    List<Product> searchUnderPrice(@Param("price") int price);

    @Query("SELECT e FROM Product e" +
            " WHERE :price <= e.price")
    List<Product> searchOverPrice(@Param("price") int price);

    @Query("SELECT e FROM Product e" +
            " WHERE e.id = (SELECT MAX(e.id) FROM e.id)")
    Product newestProduct();
}
