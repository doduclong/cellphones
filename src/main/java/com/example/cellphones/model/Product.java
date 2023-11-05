package com.example.cellphones.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String name;

    @Column(name = "description", columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String describe;

    @Column(name = "price")
    private int price;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Gallery> galleries;

}
