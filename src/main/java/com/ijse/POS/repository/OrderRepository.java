package com.ijse.POS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.POS.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


    
}

