package com.test.qp_assessment.dto;

import lombok.Data;

@Data
public class ProductDetails {
    private Long id;
    private String itemName;
    private Double price;
    private Integer quantity;
}
