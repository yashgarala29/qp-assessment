package com.test.qp_assessment.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_order_item")
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
