package com.test.qp_assessment.repository;

import com.test.qp_assessment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo  extends JpaRepository<Order,Long> {
}
