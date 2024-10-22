package com.example.SM.Controller;

import com.example.SM.Entity.Customer;
import com.example.SM.Entity.Product;
import com.example.SM.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAllProduct(){
        return productService.findAllProduct();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/searchProduct")
    public List<Product> searchProduct(@RequestParam String name){
        return productService.searchProductByName(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        try{
            Product updatedProduct = productService.updateProduct(id,product);
            return ResponseEntity.ok(updatedProduct);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
