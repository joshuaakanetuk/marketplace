package com.sellmarketplace.app.marketplace.ebay;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
public class EbayAuthService {

    private final WebClient webClient;
    private String accessToken;

    @Value("${ebay.api.client-id}")
    private String clientId;

    @Value("${ebay.api.client-secret}")
    private String clientSecret;

    @Value("${ebay.api.auth-url}")
    private String authUrl;

    public EbayAuthService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(authUrl).build();
    }

    @Scheduled(fixedRate = 3500000) // Refresh token every ~58 minutes
    public void refreshAccessToken() {
        System.out.println("Refreshing eBay API token...");

        String credentials = Base64.getEncoder()
                .encodeToString((clientId + ":" + clientSecret).getBytes());


        this.accessToken = webClient.post()
                .uri(authUrl + "/identity/v1/oauth2/token")
                .header(HttpHeaders.AUTHORIZATION, "Basic " + credentials)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue("grant_type=authorization_code&code=USER_AUTHORIZATION_CODE&redirect_uri=Joshua_Akan-Etu-JoshuaAk-Testin-idsarlv")                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> json.get("access_token").asText())
                .block();
    }

    public String getAccessToken() {
        if (accessToken == null) {
            refreshAccessToken();
        }
        System.out.println(accessToken);
        return accessToken;
    }
}
