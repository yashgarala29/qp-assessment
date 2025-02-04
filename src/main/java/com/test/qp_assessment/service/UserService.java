package com.test.qp_assessment.service;

import com.test.qp_assessment.dto.BookItem;
import com.test.qp_assessment.dto.PlaceOrder;
import com.test.qp_assessment.dto.ProductDetails;
import com.test.qp_assessment.model.Grocery;
import com.test.qp_assessment.model.Order;
import com.test.qp_assessment.model.OrderItem;
import com.test.qp_assessment.model.User;
import com.test.qp_assessment.repository.GroceryRepo;
import com.test.qp_assessment.repository.OrderRepo;
import com.test.qp_assessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private GroceryRepo groceryRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    public ResponseEntity<List<ProductDetails>> allAvailableProducts() {
        List<Grocery> groceryList = groceryRepo.findAll();
        List<ProductDetails> productDetailsList = groceryList.stream().filter(grocery -> grocery.getQuantity()>0)
                .map(grocery -> {
                    ProductDetails productDetails = new ProductDetails();
                    productDetails.setId(grocery.getId());
                    productDetails.setItemName(grocery.getItemName());
                    productDetails.setPrice(grocery.getPrice());
                    productDetails.setQuantity(grocery.getQuantity());
                    return productDetails;
                }).toList();
        return ResponseEntity.ok(productDetailsList);
    }

    public ResponseEntity<String> placeOrder(PlaceOrder placeOrder) {
        System.out.println("placeOrder = " + placeOrder);
        if(placeOrder.getBookItems().isEmpty()){
            return ResponseEntity.badRequest().body("No items to order");
        }
        List<String> validations=new ArrayList<>();
        List<BookItem> bookItems = placeOrder.getBookItems();
        for(BookItem bookItem : bookItems){
            if(bookItem.getQuantity() == null || bookItem.getQuantity()<=0 || bookItem.getProductId() == null || bookItem.getProductId() <= 0){
                validations.add("Invalid input");
                continue;
            }
            Grocery grocery = groceryRepo.findById(bookItem.getProductId()).orElse(null);
            if(grocery == null){
                validations.add("Product with id "+bookItem.getProductId()+" not found");
                continue;
            }
            if(grocery.getQuantity() < bookItem.getQuantity()){
                validations.add("Product with name "+grocery.getItemName() +" has only "+grocery.getQuantity()+" quantity");
                continue;
            }
        }
        if(!validations.isEmpty()){
            return ResponseEntity.badRequest().body(validations.toString());
        }
        User currentUser=userRepo.findById(placeOrder.getUserId()).orElse(null);
        if(currentUser==null){
            return ResponseEntity.badRequest().body("User with id "+placeOrder.getUserId()+" not found");
        }
        List<OrderItem> orderItems=new ArrayList<>();

        Order order=new Order();
        for(BookItem bookItem : bookItems){
            Grocery grocery = groceryRepo.findById(bookItem.getProductId()).orElse(null);
            grocery.setQuantity( grocery.getQuantity()-bookItem.getQuantity());
            groceryRepo.save(grocery);
            OrderItem orderItem=new OrderItem();
            orderItem.setGrocery(grocery);
            orderItem.setQuantity(bookItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);

        }
        order.setOrderItems(orderItems);
        order.setUser(currentUser);
        order.setOrderDate(LocalDateTime.now());
        orderRepo.save(order);

        return ResponseEntity.ok("Order placed successfully");

    }
}
