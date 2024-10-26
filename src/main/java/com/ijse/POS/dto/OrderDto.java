package com.ijse.POS.dto;


import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private List<Long> itemIds; // List of item IDs to be included in the order
    private LocalDateTime orderDateTime;
    private Double total_price;

    
}
