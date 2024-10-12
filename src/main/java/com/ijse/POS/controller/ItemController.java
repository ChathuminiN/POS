package com.ijse.POS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.POS.dto.ItemReqDto;
import com.ijse.POS.entity.Category;
import com.ijse.POS.entity.Item;

import com.ijse.POS.service.CategoryService;
import com.ijse.POS.service.ItemService;
import com.ijse.POS.service.StockService;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItem() {
        List<Item> itemList = itemService.getItemList();
        return ResponseEntity.status(200).body(itemList);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody ItemReqDto itemReqDto) throws Exception {
        Item item = new Item();
       
        item.setName(itemReqDto.getName());
        item.setStock(itemReqDto.getStock());
        item.setDescription(itemReqDto.getDescription());
        item.setPrice(itemReqDto.getPrice());
        
        // Get category from categoryId
        Category category = categoryService.getCategoryById(itemReqDto.getCatId());
        if (category == null) {
            return ResponseEntity.status(404).body(null); 
        } else {
            item.setCategory(category);
        }

        Item createdItem = itemService.createItem(item);
        stockService.addStock(createdItem.getId(),createdItem.getStock());//add stocks

        return ResponseEntity.status(201).body(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody ItemReqDto itemReqDto) throws Exception {
        Item item = new Item();
        item.setName(itemReqDto.getName());
        item.setStock(itemReqDto.getStock());
        item.setDescription(itemReqDto.getDescription());
        item.setPrice(itemReqDto.getPrice());
        
        // Get category from categoryId
        Category category = categoryService.getCategoryById(itemReqDto.getCatId());
        if (category == null) {
            return ResponseEntity.status(404).body(null); 
        } else {
            item.setCategory(category);
        }

        Item updatedItem = itemService.updateItem(id, item);
        if (updatedItem == null) {
            return ResponseEntity.status(404).body(null);
        } else {
            stockService.updateStock(id,updatedItem.getStock());//updating stocks
            return ResponseEntity.status(200).body(updatedItem);
        }
    }

    @DeleteMapping("/{itemId}")
public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) throws Exception {
    try {
        // Check if the item exists
        Item existingItem = itemService.getItemById(itemId);
        if (existingItem == null) {
            return ResponseEntity.status(404).build(); // Item not found
        }

        // Delete the stock entries associated with the item
        stockService.deleteStockByItemId(itemId);  // Add this method in your stockService

        // Proceed to delete the item
        itemService.deleteItem(itemId);

        return ResponseEntity.noContent().build(); // 204 No Content on success
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).build(); // 500 Internal Server Error on failure
    }
}

}
