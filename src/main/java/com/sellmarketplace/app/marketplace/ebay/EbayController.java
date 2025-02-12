package com.sellmarketplace.app.marketplace.ebay;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ebay")
@CrossOrigin(origins = "*") // Enable CORS for frontend requests
public class EbayController {
    private final EbayService ebayService;

    public EbayController(EbayService ebayService) {
        this.ebayService = ebayService;
    }

    @GetMapping("/search")
    public JsonNode searchEbay(@RequestParam String query, @RequestParam(defaultValue = "10") int limit) {
        return ebayService.searchEbayProducts(query, limit);
    }
}
