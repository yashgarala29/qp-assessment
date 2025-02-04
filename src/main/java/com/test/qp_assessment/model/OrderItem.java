package com.test.qp_assessment.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_order_item")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Grocery grocery;
    private Integer quantity;


}
