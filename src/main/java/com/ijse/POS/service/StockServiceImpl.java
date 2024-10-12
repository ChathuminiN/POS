package com.ijse.POS.service;

import com.ijse.POS.entity.Item;
import com.ijse.POS.entity.Stock;
import com.ijse.POS.repository.ItemRepository;
import com.ijse.POS.repository.StockRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Stock addStock(Long itemId, Integer quantity) throws Exception {
        Optional<Item> item = itemRepository.findById(itemId);
        if (!item.isPresent()) {
            throw new Exception("Item not found with ID: " + itemId);
        }

        Stock stock = new Stock();
        stock.setItem(item.get());
        stock.setQuantity(quantity);
        stock.setLastUpdated(LocalDateTime.now());

        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long itemId, Integer quantity) throws Exception {
        Optional<Stock> stockOptional = stockRepository.findByItemId(itemId);
        if (!stockOptional.isPresent()) {
            throw new Exception("Stock not found for item ID: " + itemId);
        }

        Stock stock = stockOptional.get();
        stock.setQuantity(quantity);
        stock.setLastUpdated(LocalDateTime.now());

        return stockRepository.save(stock);
    }

    @Override
    public Optional<Stock> getStockByItemId(Long itemId) throws Exception {
        return stockRepository.findByItemId(itemId);
    }

    @Transactional
    public void deleteStockByItemId(Long itemId) {
        stockRepository.deleteByItemId(itemId);
    }


   

}
