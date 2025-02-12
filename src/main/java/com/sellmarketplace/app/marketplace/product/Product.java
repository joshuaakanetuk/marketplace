package com.sellmarketplace.app.marketplace.product;

import com.sellmarketplace.app.marketplace.category.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    // This is the description that the manufacturer includes with the product.
    // This is not for listing information (info related to the unique listing).
    @Column
    private String description;

    //    @ManyToOne
    //    @JoinColumn(name = "category_id", nullable = false)
    //    private Category category;

    @Column
    private int upc;
}
