package com.sellmarketplace.app.marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class OAuth2ClientConfig {

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient tokenResponseClient =
                new DefaultAuthorizationCodeTokenResponseClient();

        // eBay requires specific headers and content type
        OAuth2AuthorizationCodeGrantRequestEntityConverter requestEntityConverter =
                new OAuth2AuthorizationCodeGrantRequestEntityConverter() {
                    @Override
                    public RequestEntity<?> convert(OAuth2AuthorizationCodeGrantRequest oauth2Request) {
                        RequestEntity<?> entity = super.convert(oauth2Request);
                        HttpHeaders headers = new HttpHeaders();
                        headers.putAll(entity.getHeaders());

                        // eBay requires Content-Type: application/x-www-form-urlencoded
                        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                        // Some eBay applications may need this header
                        headers.add("Accept", "application/json");

                        return new RequestEntity<>(
                                entity.getBody(),
                                headers,
                                entity.getMethod(),
                                entity.getUrl()
                        );
                    }
                };

        tokenResponseClient.setRequestEntityConverter(requestEntityConverter);

        // Configure Rest Template with both form and JSON converters
        RestTemplate restTemplate = new RestTemplate(Arrays.asList(
                new FormHttpMessageConverter(),
                new MappingJackson2HttpMessageConverter()
        ));

        tokenResponseClient.setRestOperations(restTemplate);

        return tokenResponseClient;
    }
}