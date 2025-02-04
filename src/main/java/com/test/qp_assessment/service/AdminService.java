package com.test.qp_assessment.service;

import com.test.qp_assessment.dto.ProductDetails;
import com.test.qp_assessment.model.Grocery;
import com.test.qp_assessment.repository.GroceryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private GroceryRepo groceryRepo;
    public ResponseEntity<String> addProduct(ProductDetails productDetails) {
            if(productDetails.getPrice()==null || productDetails.getQuantity()==null || productDetails.getItemName()==null){
                return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
            }
            Grocery grocery=new Grocery();
            grocery.setPrice(productDetails.getPrice());
            grocery.setQuantity(productDetails.getQuantity());
            grocery.setItemName(productDetails.getItemName());
            groceryRepo.save(grocery);
            return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
        }

    public ResponseEntity<List<ProductDetails>> getAllProducts() {
        List<Grocery> groceryList=groceryRepo.findAll();
        List<ProductDetails> productDetailsList= groceryList.stream().map(grocery -> {
            ProductDetails productDetails=new ProductDetails();
            productDetails.setId(grocery.getId());
            productDetails.setItemName(grocery.getItemName());
            productDetails.setPrice(grocery.getPrice());
            productDetails.setQuantity(grocery.getQuantity());
            return productDetails;
        }).toList();
        return new ResponseEntity<>(productDetailsList,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteProduct(Long productId) {
        Grocery grocery=groceryRepo.findById(productId).orElse(null);
        if(grocery==null){
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }
        groceryRepo.deleteById(productId);
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }

    public ResponseEntity<String> updateProduct(ProductDetails productDetails) {
        if(productDetails.getId()==null){
            return new ResponseEntity<>("Invalid input",HttpStatus.BAD_REQUEST);
        }
        Grocery grocery=groceryRepo.findById(productDetails.getId()).orElse(null);
        if(grocery==null){
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }
        if(productDetails.getItemName()!=null){
            grocery.setItemName(productDetails.getItemName());
        }
        if(productDetails.getPrice()!=null){
            grocery.setPrice(productDetails.getPrice());
        }
        if(productDetails.getQuantity()!=null){
            grocery.setQuantity(productDetails.getQuantity());
        }
        groceryRepo.save(grocery);
        return new ResponseEntity<>("Product updated successfully",HttpStatus.OK);
    }

    public ResponseEntity<String> updateProductQuantity(ProductDetails productDetails) {
        if(productDetails.getId()==null || productDetails.getQuantity()==null){
            return new ResponseEntity<>("Invalid input",HttpStatus.BAD_REQUEST);
        }
        Grocery grocery=groceryRepo.findById(productDetails.getId()).orElse(null);
        if(grocery==null){
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }
        if(productDetails.getQuantity()!=null){
            grocery.setQuantity(productDetails.getQuantity());
        }
        groceryRepo.save(grocery);
        return new ResponseEntity<>("Product quantity updated successfully",HttpStatus.OK);
    }
}
