package com.ijse.POS.service;

import com.ijse.POS.entity.Stock;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public interface StockService {

    // Add stock
    Stock addStock(Long itemId, Integer quantity) throws Exception;

    // Update stock
    Stock updateStock(Long itemId, Integer quantity) throws Exception;

    // Retrieve stock by item ID
    Optional<Stock> getStockByItemId(Long itemId) throws Exception;
}

