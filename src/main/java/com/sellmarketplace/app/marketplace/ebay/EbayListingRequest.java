package com.sellmarketplace.app.marketplace.ebay;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EbayListingRequest {
    private String sku;
    private Availability availability;
    private Product product;
    private PricingSummary pricingSummary;
    private String condition;
    private PackageWeightAndSize packageWeightAndSize;
    private Category category;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Availability {
        private ShipToLocationAvailability shipToLocationAvailability;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShipToLocationAvailability {
        private int quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private String title;
        private String description;
        private Map<String, List<String>> aspects;
        private List<String> imageUrls;
        private String brand;
        private String mpn;
        private String epid;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PricingSummary {
        private Price price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Price {
        private String value;
        private String currency;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PackageWeightAndSize {
        private Dimensions dimensions;
        private Weight weight;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dimensions {
        private double height;
        private double length;
        private double width;
        private String unit;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weight {
        private double value;
        private String unit;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category {
        private String categoryId;
    }
}
