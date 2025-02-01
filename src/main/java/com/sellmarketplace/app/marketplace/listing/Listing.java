package com.sellmarketplace.app.marketplace.listing;

import com.sellmarketplace.app.marketplace.product.Product;
import jakarta.persistence.*;
import lombok.Data;

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

    @Column(columnDefinition = "JSONB")
    private String shippingDetails;

    @Column(columnDefinition = "JSONB")
    private String paymentMethods;

    @Column(columnDefinition = "JSONB")
    private String returnsPolicy;
}
