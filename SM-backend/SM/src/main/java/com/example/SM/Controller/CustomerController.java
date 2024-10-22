package com.example.SM.Controller;

import com.example.SM.Entity.Customer;
import com.example.SM.Entity.Product;
import com.example.SM.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> findAllCustomer() {
        return customerService.findAllCustomer();
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @GetMapping("/searchCustomer")
    public List<Customer> searchCustomer(@RequestParam String name){
        return customerService.searchCustomerByName(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        try{
            Customer updatedCustomer = customerService.updateCustomer(id,customer);
            return ResponseEntity.ok(updatedCustomer);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

}
