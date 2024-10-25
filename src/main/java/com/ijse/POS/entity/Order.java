package com.ijse.POS.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
@Table(name="orders")// we cant use table name as "order"---bcoz,order is key word in mysql,therefore it should be other "orders"
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDateTime;
    private Double total_price;

    @PrePersist //to set default value---to avoid back date orders
    protected void onCreate(){
        if(orderDateTime==null){
            this.orderDateTime=LocalDateTime.now();

        }
        
    }
    @ManyToMany
    @JoinTable(
        name="Order_Item",
        joinColumns = @JoinColumn(name="order_id"),
        inverseJoinColumns=@JoinColumn(name="item-id")
    )
    private List<Item>orderedItems;
    
}
