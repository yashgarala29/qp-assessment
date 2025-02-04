package com.test.qp_assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrder {
    List<BookItem> bookItems;
    Long userId;
}
