package com.sellmarketplace.app.marketplace.ebay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EbayListingService {

    private final WebClient webClient;
    private final EbayAuthService ebayAuthService;

    @Value("${ebay.api.base-url}")
    private String ebayApiUrl;

    public EbayListingService(WebClient.Builder webClientBuilder, EbayAuthService ebayAuthService) {
        this.webClient = webClientBuilder.baseUrl(ebayApiUrl).build();
        this.ebayAuthService = ebayAuthService;
    }

    public String listProduct(EbayListingRequest request) {
        String accessToken = "";

        return webClient.put()
                .uri("https://api.ebay.com/sell/inventory/v1/inventory_item/" + request.getSku())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_LANGUAGE, "en-US")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
