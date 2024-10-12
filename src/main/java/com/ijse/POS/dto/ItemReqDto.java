package com.ijse.POS.dto;



import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ItemReqDto {
    
    private String name;
    private Integer stock;
    private String description;   
    private Double price;  
    private long catId;    
    
}
