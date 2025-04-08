package com.sellmarketplace.app.marketplace.listing;

import com.sellmarketplace.app.marketplace.product.Product;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.Map;

@Data
@Entity
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String externalId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String listingType;

    private Integer duration;

    private Integer quantity;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> shippingDetails = new HashMap<>();

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> paymentMethods = new HashMap<>();

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> returnsPolicy = new HashMap<>();
}
