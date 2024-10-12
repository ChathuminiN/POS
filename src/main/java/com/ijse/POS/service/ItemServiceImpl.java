package com.ijse.POS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.POS.entity.Item;
import com.ijse.POS.repository.ItemRepository;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    
    private ItemRepository itemRepository;

    @Override
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Long Id, Item item) {
        Item existingItem=itemRepository.findById(Id).orElse(null);
        if(existingItem==null){
            return null;
        }else{
            existingItem.setName(item.getName());
            existingItem.setCategory(item.getCategory());
            existingItem.setStock(item.getStock());
            existingItem.setDescription(item.getDescription());
            existingItem.setPrice(item.getPrice());
            
            
            return itemRepository.save(existingItem);

        }
    }

    @Override
    public Item getItemById(Long Id) {
        return itemRepository.findById(Id).orElse(null);
    }

    @Override
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    
    
}
