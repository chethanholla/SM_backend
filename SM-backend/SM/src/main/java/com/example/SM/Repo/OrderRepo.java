package com.example.SM.Repo;

import com.example.SM.Entity.Order;
import com.example.SM.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByCustomerName(String name);
}
