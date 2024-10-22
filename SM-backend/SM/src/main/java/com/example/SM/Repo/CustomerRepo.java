package com.example.SM.Repo;

import com.example.SM.Entity.Customer;
import com.example.SM.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContainingIgnoreCase(String name);
}
