package com.sellmarketplace.app.marketplace.inventory;

import com.sellmarketplace.app.marketplace.product.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;



}
