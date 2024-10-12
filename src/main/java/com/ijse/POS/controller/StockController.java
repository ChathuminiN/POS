package com.ijse.POS.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ijse.POS.dto.StockReqDto;
import com.ijse.POS.entity.Stock;
import com.ijse.POS.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    // Adding stock for an item
    @PostMapping("/add")
  
    public ResponseEntity<Stock> addStock(@RequestBody StockReqDto stockReqDto) {
        try {
            Long itemId = stockReqDto.getItemId();
            Integer quantity = stockReqDto.getQuantity();
            Stock stock = stockService.addStock(itemId, quantity);
            return new ResponseEntity<>(stock, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // updating stock for an item
    @PutMapping("/update")
    public ResponseEntity<Stock> updateStock(@RequestBody StockReqDto stockReqDto) {
        try {
            Long itemId = stockReqDto.getItemId();
            Integer quantity = stockReqDto.getQuantity();
            Stock stock = stockService.updateStock(itemId, quantity);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //  Getting stock information for an item
    @GetMapping("/get")
    public ResponseEntity<Stock> getStockByItemId(@RequestBody StockReqDto stockReqDto) {
        try {
            Long itemId = stockReqDto.getItemId();
            Optional<Stock> stock = stockService.getStockByItemId(itemId);
            return stock.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
