package com.test.qp_assessment.controller;

import com.test.qp_assessment.dto.BookItem;
import com.test.qp_assessment.dto.PlaceOrder;
import com.test.qp_assessment.dto.ProductDetails;
import com.test.qp_assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/allAvailableProducts")
    public ResponseEntity<List<ProductDetails>> allAvailableProducts() {
        return userService.allAvailableProducts();
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody PlaceOrder placeOrder) {
        return userService.placeOrder(placeOrder);
    }

}
