package com.ijse.POS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.POS.entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    // Method to find sales by a specific user
    List<Sales> findBySoldById(Long userId);

    // Method to find sales by a specific item
    List<Sales> findByItemId(Long itemId);
}
