package com.ijse.POS.service;

import com.ijse.POS.entity.Sales;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface SalesService {
    
    // Method to add an item to the cart
    String addToCart(Long itemId, Integer quantity);
    
    // Method to checkout and generate invoices
    List<Sales> checkout();
    
    // Method to get sales by date range
    List<Sales> getSalesByDate(LocalDateTime startDate, LocalDateTime endDate);
    
    // Method to get sales by user
    //List<Sales> getSalesByUser(Long userId);
}
