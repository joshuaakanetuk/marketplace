package com.sellmarketplace.app.marketplace.ebay;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

@Service
public class EbayService {
    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";

    private static final String SEARCH_URL = "https://api.ebay.com/buy/browse/v1/item_summary/search";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Search eBay Products
     */
    public JsonNode searchEbayProducts(String query, int limit) {
        String token = "v^1.1#i^1#p^3#I^3#f^0#r^0#t^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";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        String url = SEARCH_URL + "?q=" + query + "&limit=" + limit;

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        try {
            return objectMapper.readTree(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
