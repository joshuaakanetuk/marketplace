package com.sellmarketplace.app.marketplace.ebay;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ebay")
@CrossOrigin(origins = "*") // Enable CORS for frontend requests
public class EbayController {
    private final EbayService ebayService;
    private final EbayListingService ebayListingService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EbayController(EbayService ebayService, EbayListingService ebayListingService) {
        this.ebayService = ebayService;
        this.ebayListingService = ebayListingService;
    }

    @GetMapping("/search")
    public JsonNode searchEbay(@RequestParam String query, @RequestParam(defaultValue = "5") int limit) {
        return ebayService.searchEbayProducts(query, limit);
    }

    @PostMapping("/list-product")
    public String listProduct(@RequestBody EbayListingRequest request) {
        System.out.println(request);
        return ebayListingService.listProduct(request);
    }
}
