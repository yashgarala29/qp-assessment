package com.test.qp_assessment.controller;

import com.test.qp_assessment.dto.ProductDetails;
import com.test.qp_assessment.dto.UserDetails;
import com.test.qp_assessment.model.Role;
import com.test.qp_assessment.service.AdminService;
import com.test.qp_assessment.service.AuthenticatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    AuthenticatService authenticatService;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(  @RequestBody ProductDetails productDetails) {
        if(!authenticatService.authenticateUser(productDetails.getUserId(), Role.ADMIN)){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return adminService.addProduct(productDetails);
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDetails>> getAllProducts(  @RequestBody UserDetails userDetails) {

        if(!authenticatService.authenticateUser(userDetails.getUserId(), Role.ADMIN)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return adminService.getAllProducts();
    }
    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId, @RequestBody UserDetails userDetails) {
        if(!authenticatService.authenticateUser(userDetails.getUserId(), Role.ADMIN)){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return adminService.deleteProduct(productId);
    }
    @PatchMapping("/updateProduct")
    public ResponseEntity<String> updateProduct( @Validated @RequestBody ProductDetails productDetails) {
        if(!authenticatService.authenticateUser(productDetails.getUserId(), Role.ADMIN)){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return adminService.updateProduct(productDetails);
    }
    @PatchMapping("/updateProductQuantity")
    public ResponseEntity<String> updateProductQuantity(@RequestBody ProductDetails productDetails) {
        if(!authenticatService.authenticateUser(productDetails.getUserId(), Role.ADMIN)){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return adminService.updateProductQuantity(productDetails);
    }




}
