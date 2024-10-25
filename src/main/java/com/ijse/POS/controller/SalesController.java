package com.ijse.POS.controller;

import com.ijse.POS.entity.Sales;
import com.ijse.POS.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @PostMapping("/add-to-cart/{itemId}/{quantity}")
    public ResponseEntity<String> addToCart(@PathVariable Long itemId, @PathVariable Integer quantity) {
        String response = salesService.addToCart(itemId, quantity);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkout")
    public ResponseEntity<List<Sales>> checkout() {
        List<Sales> completedSales = salesService.checkout();
        if (completedSales == null) {
            return ResponseEntity.badRequest().body(null); // No items in the cart
        }
        return ResponseEntity.ok(completedSales);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<Sales>> getSalesByDate(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<Sales> salesList = salesService.getSalesByDate(startDate, endDate);
        return ResponseEntity.ok(salesList);
    }

    // @GetMapping("/by-user/{userId}")
    // public ResponseEntity<List<Sales>> getSalesByUser(@PathVariable Long userId) {
    //     List<Sales> salesList = salesService.getSalesByUser(userId); // Uncomment this in SalesRepository
    //     return ResponseEntity.ok(salesList);
    // }
}
