package com.example.SM.Service;

import com.example.SM.DTO.OrderRequest;
import com.example.SM.Entity.Customer;
import com.example.SM.Entity.Order;
import com.example.SM.Entity.Product;
import com.example.SM.Repo.CustomerRepo;
import com.example.SM.Repo.OrderRepo;
import com.example.SM.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public List<Order> findAllOrder(){
        return orderRepo.findAll();
    }

    public Order createOrder(Order order){

        if(order.getProduct().getId() == null || order.getCustomer().getId() == null){
            throw new IllegalArgumentException("Product and customer ID must not be null");
        }

        Product product = productRepo.findById(order.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Customer customer = customerRepo.findById(order.getCustomer().getId())
                .orElseThrow(()->new RuntimeException("Customer not found"));

        if(product.getQuantity() < order.getQuantity()){
            throw new RuntimeException("Not enough product quantity");
        }

        product.setQuantity(product.getQuantity()- order.getQuantity());
        order.setQuantity(order.getQuantity());

        productRepo.save(product);

        order.setProduct(product);
        order.setCustomer(customer);


        return orderRepo.save(order);
    }

    public Order updateOrder(Long id, Order updateOrder){
        Optional<Order> existingOrderOpt = orderRepo.findById(id);

        if(!existingOrderOpt.isPresent()){
            throw new IllegalArgumentException("Order with id " + id + " not found");
        }

        Order existingOrder = existingOrderOpt.get();

        Product product = existingOrder.getProduct();

        Long oldOrderQuantity = existingOrder.getQuantity();
        Long newOrderQuantity = updateOrder.getQuantity();
        Long quantityDifference = oldOrderQuantity - newOrderQuantity;

        if(product != null){
            product.setQuantity(product.getQuantity() + quantityDifference);

            productRepo.save(product);
        }

        existingOrder.setQuantity(updateOrder.getQuantity());

        if(updateOrder.getProduct() != null && updateOrder.getProduct().getId() != null){
            Product newProduct = productRepo.findById(updateOrder.getProduct().getId())
                    .orElseThrow(()-> new IllegalArgumentException("Product not found"));

            existingOrder.setProduct(newProduct);
        }

        if(updateOrder.getCustomer() != null && updateOrder.getCustomer().getId() != null){
            Customer customer = customerRepo.findById(updateOrder.getCustomer().getId())
                    .orElseThrow(()-> new IllegalArgumentException("Customer not found"));

            existingOrder.setCustomer(customer);
        }

        return orderRepo.save(existingOrder);
    }

    public void deleteCustomer(Long id){
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Failed to Delete"));
        Product product = productRepo.findById(order.getProduct().getId())
                .orElseThrow(() -> new NullPointerException("Product not found"));

        product.setQuantity(order.getQuantity() + product.getQuantity());
        productRepo.save(product);

//        order.setProduct(product);

        orderRepo.deleteById(id);
    }

    public List<Order> viewCustomerHistory(String customerName){
        return orderRepo.findByCustomerName(customerName);
    }
}
