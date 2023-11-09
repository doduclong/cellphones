package com.example.cellphones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_gallery")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gallery {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Product product;

    @Lob
    @Column(name = "image")
    private String image;
}