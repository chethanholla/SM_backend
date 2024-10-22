package com.example.SM.Service;

import com.example.SM.DTO.OrderRequest;
import com.example.SM.Entity.Customer;
import com.example.SM.Entity.Order;
import com.example.SM.Entity.Product;
import com.example.SM.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Long productId, Product updatedProduct){
        Optional<Product> existingProductOpt = productRepo.findById(productId);

        if(existingProductOpt.isPresent()){
            Product existingProduct = existingProductOpt.get();

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());

            return productRepo.save(existingProduct);
        }else{
            throw new RuntimeException("Product not found with ID: "+ productId);
        }
    }

    public List<Product> searchProductByName(String name) {
        return productRepo.findByNameContainingIgnoreCase(name);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
