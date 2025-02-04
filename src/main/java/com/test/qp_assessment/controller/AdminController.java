package com.test.qp_assessment.controller;

import com.test.qp_assessment.dto.ProductDetails;
import com.test.qp_assessment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDetails productDetails) {
        return adminService.addProduct(productDetails);
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDetails>> getAllProducts() {
        return adminService.getAllProducts();
    }
    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        return adminService.deleteProduct(productId);
    }
    @PatchMapping("/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDetails productDetails) {
        return adminService.updateProduct(productDetails);
    }
    @PatchMapping("/updateProductQuantity")
    public ResponseEntity<String> updateProductQuantity(@RequestBody ProductDetails productDetails) {
        return adminService.updateProductQuantity(productDetails);
    }




}
