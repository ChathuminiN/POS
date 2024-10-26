package com.ijse.POS.controller;

import com.ijse.POS.dto.OrderDto;
import com.ijse.POS.entity.Item;
import com.ijse.POS.entity.Order;
import com.ijse.POS.service.ItemService;
import com.ijse.POS.service.OrderService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*") // Allow requests from any origin
@RequestMapping("/orders") // Base URL for this controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    private static final Logger logger = Logger.getLogger(OrderController.class.getName());

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        logger.info("Fetching all orders");
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok()
            .cacheControl(CacheControl.noCache()) // Disable caching
            .body(orders);
    }

    // Create a new order
    @PostMapping
    @Transactional
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        // Check if orderDto contains item IDs
        if (orderDto.getItemIds() == null || orderDto.getItemIds().isEmpty()) {
            logger.warning("No item IDs provided in the order.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No item IDs provided");
        }

        Order order = new Order();
        List<Long> itemIds = orderDto.getItemIds();
        double totalPrice = 0.0;
        List<Item> orderedItems = new ArrayList<>();

        // Loop through each item ID and fetch the item from the database
        for (Long itemId : itemIds) {
            Item item = itemService.getItemById(itemId);
            if (item != null) {
                orderedItems.add(item);
                totalPrice += item.getPrice();  // Add item price to the total
            } else {
                logger.warning("Item not found with ID: " + itemId); // Log missing item
            }
        }

        // If no valid items were found, return a 400 Bad Request response
        if (orderedItems.isEmpty()) {
            logger.warning("No valid items found in the order.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No valid items found for the order.");
        }

        // Set order details and save
        order.setOrderedItems(orderedItems);
        order.setTotal_price(totalPrice);
        Order savedOrder = orderService.createOrder(order); // Save order

        logger.info("Order created successfully with total price: " + totalPrice);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }
}
