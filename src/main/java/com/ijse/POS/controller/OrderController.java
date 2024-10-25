package com.ijse.POS.controller;

import com.ijse.POS.dto.OrderDto;
import com.ijse.POS.entity.Item;
import com.ijse.POS.entity.Order;
import com.ijse.POS.service.ItemService;
import com.ijse.POS.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Allow requests from your frontend
@RequestMapping("/orders") // Base URL for this controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {
        if (orderDto.getItemIds() == null || orderDto.getItemIds().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return 400 if no item IDs are provided
        }

        Order order = new Order();
        List<Long> itemIds = orderDto.getItemIds();
        order.setTotal_price(0.0);
        List<Item> orderedItems = new ArrayList<>();

        // Iterate through item IDs to create order
        for (Long itemId : itemIds) {
            Item item = itemService.getItemById(itemId);

            if (item != null) {
                orderedItems.add(item);
                order.setTotal_price(order.getTotal_price() + item.getPrice());
            } else {
                // Log or throw a custom exception for a missing item
                // You might want to handle this more gracefully in a production app
                System.out.println("Item not found: " + itemId);
            }
        }

        // Check if there are any valid items before saving
        if (orderedItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Return 400 if no valid items were found
        }

        order.setOrderedItems(orderedItems);
        orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order); // Return the created order
    }
}
