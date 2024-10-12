package com.ijse.POS.repository;

import com.ijse.POS.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    
    // method to find sales by a specific date range
    List<Sales> findBySoldAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // method to find sales made by a specific user
    // List<Sales> findBySoldById(Long userId);
    
    
}

