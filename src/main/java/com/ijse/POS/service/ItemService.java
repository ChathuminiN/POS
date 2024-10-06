package com.ijse.POS.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.ijse.POS.entity.Item;
@Service
public interface ItemService {    
    List<Item> getItemList();//reading
    Item createItem(Item item);//creating
    Item updateItem(Long Id,Item item);
    Item getItemById(Long Id);
    void deleteItem(Long itemId);
    
    
        
    
}
    

