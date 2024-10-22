package com.example.SM.Repo;

import com.example.SM.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
}
