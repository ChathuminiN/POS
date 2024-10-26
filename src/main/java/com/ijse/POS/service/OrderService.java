package com.ijse.POS.service;

import com.ijse.POS.entity.Order;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    List<Order> getAllOrders(); // Retrieve all orders
    Order createOrder(Order order); // Create a new order
}


