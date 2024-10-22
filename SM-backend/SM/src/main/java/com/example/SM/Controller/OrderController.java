package com.example.SM.Controller;

import com.example.SM.DTO.OrderRequest;
import com.example.SM.Entity.Order;
import com.example.SM.Entity.Product;
import com.example.SM.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @GetMapping
    public List<Order> findAllOrder(){
        return orderService.findAllOrder();
    }

    @PostMapping
    public ResponseEntity<Order> createdOrder(@RequestBody Order order){
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/history")
    public List<Order> searchCustomerHistory(@RequestParam String name){
        return orderService.viewCustomerHistory(name);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order){
        Order updatedOrder = orderService.updateOrder(id,order);
        return ResponseEntity.ok(updatedOrder);
    }
}
