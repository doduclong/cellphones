package com.example.cellphones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",unique = true, nullable = false, columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String name;

    @Column(name = "description", columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String describe;

    @Column(name = "price")
    private int price;

    @Column(name = "classification")
    private String classification;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Size> sizes = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Gallery> galleries = new ArrayList<>();

}
