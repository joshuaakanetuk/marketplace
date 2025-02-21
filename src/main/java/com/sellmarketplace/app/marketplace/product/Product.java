package com.sellmarketplace.app.marketplace.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.sellmarketplace.app.marketplace.category.Category;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.Map;

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

    @Type(JsonType.class)
    @Column(columnDefinition = "JSONB")
    private Map<String, Object> specifications;

    @Column
    private int upc;
}
