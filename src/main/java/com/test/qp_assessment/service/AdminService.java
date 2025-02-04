package com.test.qp_assessment.service;

import com.test.qp_assessment.dto.ProductDetails;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public void addProduct() {
        ProductDetails productDetails=new ProductDetails();
        productDetails.setItemName("Milk");
        System.out.println("Product added successfully"+productDetails.getItemName());
    }
}
