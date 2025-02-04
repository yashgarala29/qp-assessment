package com.test.qp_assessment.controller;

import com.test.qp_assessment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct() {
        adminService.addProduct();
        return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
    }




}
