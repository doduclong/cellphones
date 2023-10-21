package com.example.cellphones.model;

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
public class DeliveryAddress {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "address", columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String address;

    @Column(name = "full_name", columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;
}
