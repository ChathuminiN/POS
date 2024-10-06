package com.ijse.POS.dto;



import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ItemReqDto {
    
    private String name;
    private String description;   
    private Double price;  
    private long catId;    
    
}
