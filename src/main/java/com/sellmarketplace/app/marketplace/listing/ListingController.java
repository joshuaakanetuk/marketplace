package com.sellmarketplace.app.marketplace.listing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListingController {
    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/listings")
    public List<Listing> getListings() {
        return listingService.getAllListings();
    }

}
