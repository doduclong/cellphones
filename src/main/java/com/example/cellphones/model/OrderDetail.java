package com.example.cellphones.model;
import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "tbl_order_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne
    private Product product;

    @Column(name = "quantity")
    private int quantity;

}
