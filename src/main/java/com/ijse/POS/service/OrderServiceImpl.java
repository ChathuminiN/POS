package com.ijse.POS.service;

import com.ijse.POS.entity.Order;
import com.ijse.POS.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Override
    public List<Order> getAllOrders() {
        logger.info("Fetching all orders from the database");
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        logger.info("Creating new order in the database with total price: " + order.getTotal_price());
        return orderRepository.save(order); // Save the order to the database
    }
}
