package com.ijse.POS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockReqDto {
    private Long itemId; // ID of the item
    private Integer quantity; // Quantity to add or update
}


