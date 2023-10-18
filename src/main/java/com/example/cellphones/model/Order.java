package com.example.cellphones.model;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderDetail> listOrderDetail;

    @Column(name = "total")
    private long total;

    @Column(name = "payment", columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String payment;

    @Column(name = "receiver_name", columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String receiverName;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    @Column(name = "receiver_address", columnDefinition = "varchar(255) CHARACTER SET utf8")
    private String receiverAddress;

    @Column(name = "time_order")
    private String timeOrder;
}
