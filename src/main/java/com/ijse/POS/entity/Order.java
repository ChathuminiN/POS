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
@Table(name = "orders") // Avoid "order" keyword as it's reserved in SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDateTime;
    private Double total_price;

    @PrePersist // To set default value and prevent backdate orders
    protected void onCreate() {
        if (orderDateTime == null) {
            this.orderDateTime = LocalDateTime.now();
        }
    }

    @ManyToMany
    @JoinTable(
        name = "Order_Item",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id") // Changed "item-id" to "item_id"
    )
    private List<Item> orderedItems;
}
