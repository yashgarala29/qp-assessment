package com.test.qp_assessment.controller;

import com.test.qp_assessment.dto.BookItem;
import com.test.qp_assessment.dto.PlaceOrder;
import com.test.qp_assessment.dto.ProductDetails;
import com.test.qp_assessment.model.Role;
import com.test.qp_assessment.service.AuthenticatService;
import com.test.qp_assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticatService authenticatService;


    @GetMapping("/allAvailableProducts")
    public ResponseEntity<List<ProductDetails>> allAvailableProducts() {
        return userService.allAvailableProducts();
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody PlaceOrder placeOrder) {
        if(!authenticatService.authenticateUser(placeOrder.getUserId(), Role.USER)){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return userService.placeOrder(placeOrder);
    }

}
