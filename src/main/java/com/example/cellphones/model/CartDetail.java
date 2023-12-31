package com.example.cellphones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "tbl_cart_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @OneToOne
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "size")
    private String size;
}
