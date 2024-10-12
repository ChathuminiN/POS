package com.ijse.POS.service;

import com.ijse.POS.entity.Item;
import com.ijse.POS.entity.Sales;
import com.ijse.POS.repository.ItemRepository;
import com.ijse.POS.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ItemRepository itemRepository; 

    

    // Temporary cart to hold items before checkout
    private List<Sales> cart = new ArrayList<>();

    @Override
    public String addToCart(Long itemId, Integer quantity) {
        Item item = itemRepository.findById(itemId).orElse(null);

        

        if (item == null || item.getStock() < quantity) {
            return "Item not found or insufficient stock.";
        }

        Sales sale = new Sales();
        sale.setItem(item);
        sale.setQuantity(quantity);
        sale.setTotalPrice(item.getPrice() * quantity);
        sale.setSoldAt(LocalDateTime.now());

        cart.add(sale);
        return "Item added to cart.";
    }

    @Override
    public List<Sales> checkout() {
        if (cart.isEmpty()) {
            return null; // No items in the cart
        }

        List<Sales> completedSales = new ArrayList<>();
        for (Sales sale : cart) {
            // Save each sale to the database
            Sales savedSale = salesRepository.save(sale);
            completedSales.add(savedSale);

            // Update stock (assuming Item entity has a method to decrease stock)
            Item item = sale.getItem();
            item.setStock(item.getStock() - sale.getQuantity());
            itemRepository.save(item); // Save updated item
        }

        // Clear the cart after checkout
        cart.clear();
        return completedSales;
    }

    @Override
    public List<Sales> getSalesByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return salesRepository.findBySoldAtBetween(startDate, endDate);
    }

    // @Override
    // public List<Sales> getSalesByUser(Long userId) {
    //     return salesRepository.findBySoldById(userId); // Uncomment this in SalesRepository
    // }
}
