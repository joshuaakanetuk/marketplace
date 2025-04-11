package com.sellmarketplace.app.marketplace.config;

import com.sellmarketplace.app.marketplace.category.Category;
import com.sellmarketplace.app.marketplace.category.CategoryRepository;
import com.sellmarketplace.app.marketplace.listing.Listing;
import com.sellmarketplace.app.marketplace.listing.ListingRepository;
import com.sellmarketplace.app.marketplace.product.Product;
import com.sellmarketplace.app.marketplace.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Profile("dev") // Ensures it only runs in the development profile
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final ListingRepository listingRepository;
    private final CategoryRepository categoryRepository;

    public DataSeeder(ProductRepository productRepository, ListingRepository listingRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.listingRepository = listingRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (listingRepository.count() == 0) { // Prevent duplicate inserts
            System.out.println("Seeding development data...");

            // Create a Category
            Category category = new Category();
            category.setCategoryName("Electronics");
            categoryRepository.save(category);

            // Create First Product
            Product product1 = new Product();
            product1.setName("Wireless Mouse");
            product1.setDescription("Ergonomic wireless mouse with long battery life.");
//            product1.setCategory(category);
            product1.setBrand("Logitech");
//            product1.setCondition("New");
            productRepository.save(product1);

            // Create Second Product
            Product product2 = new Product();
            product2.setName("Mechanical Keyboard");
            product2.setDescription("RGB mechanical keyboard with blue switches.");
//            product2.setCategory(category);
            product2.setBrand("Keychron");
//            product2.setCondition("New");
            productRepository.save(product2);

            // Create First Listing
//            Listing listing1 = new Listing();
//            listing1.setProduct(product1);
//            listing1.setPrice(29.99);
//            listing1.setListingType("Fixed Price");
//            listing1.setDuration(30);
//            listing1.setQuantity(10);
//            listing1.setShippingDetails("{\"shippingService\": \"Standard Shipping\", \"cost\": 5.99}");
//            listing1.setPaymentMethods("{\"methods\": [\"PayPal\", \"Credit Card\"]}");
//            listing1.setReturnsPolicy("{\"returnsAccepted\": true, \"days\": 30}");
//            listingRepository.save(listing1);
//
//            // Create Second Listing
//            Listing listing2 = new Listing();
//            listing2.setProduct(product2);
//            listing2.setPrice(89.99);
//            listing2.setListingType("Fixed Price");
//            listing2.setDuration(30);
//            listing2.setQuantity(5);
//            listing2.setShippingDetails("{\"shippingService\": \"Free Shipping\", \"cost\": 0}");
//            listing2.setPaymentMethods("{\"methods\": [\"PayPal\", \"Credit Card\"]}");
//            listing2.setReturnsPolicy("{\"returnsAccepted\": true, \"days\": 30}");
//            listingRepository.save(listing2);

            System.out.println("Development data seeded.");
        } else {
            System.out.println("Skipping development data seeding: Listings already exist.");
        }
    }
}

