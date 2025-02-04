package com.test.qp_assessment.repository;

import com.test.qp_assessment.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepo extends JpaRepository<Grocery,Long> {

}
