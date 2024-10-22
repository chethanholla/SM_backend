package com.example.SM.Service;

import com.example.SM.Entity.Customer;
import com.example.SM.Entity.Product;
import com.example.SM.Repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> findAllCustomer() {
        return customerRepo.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public List<Customer> searchCustomerByName(String name){
        return customerRepo.findByNameContainingIgnoreCase(name);
    }

    public void deleteCustomer(Long id){
        customerRepo.deleteById(id);
    }

    public Customer updateCustomer(Long customerId, Customer updatedcustomer){
        Optional<Customer> existingCustomerOpt = customerRepo.findById(customerId);

        if(existingCustomerOpt.isPresent()){
            Customer existingCustomer = existingCustomerOpt.get();

            existingCustomer.setName(updatedcustomer.getName());
            existingCustomer.setEmail(updatedcustomer.getEmail());

            return customerRepo.save(existingCustomer);
        }else{
            throw new RuntimeException("Customer not found with ID: "+ customerId);
        }
    }
}
