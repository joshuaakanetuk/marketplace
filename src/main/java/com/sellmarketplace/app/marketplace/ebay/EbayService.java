package com.sellmarketplace.app.marketplace.ebay;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;
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
        String token = "";

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
