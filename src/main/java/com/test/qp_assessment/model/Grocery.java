package com.test.qp_assessment.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_grocery")
@Data
public class Grocery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private Double price;
    private Integer quantity;

}
