package com.ijse.POS.repository;

import com.ijse.POS.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // Find stock by item ID
    Optional<Stock> findByItemId(Long itemId);
    
}
