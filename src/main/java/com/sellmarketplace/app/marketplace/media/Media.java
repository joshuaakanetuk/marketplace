package com.sellmarketplace.app.marketplace.media;

import com.sellmarketplace.app.marketplace.product.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String url;

    private Boolean isPrimary;
}


