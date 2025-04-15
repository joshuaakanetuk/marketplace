package com.sellmarketplace.app.marketplace.account;

import com.sellmarketplace.app.marketplace.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private LinkedAccountRepository linkedAccountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId(); // "ebay"
        String providerUserId = oAuth2User.getAttribute("username"); // may also be "user_id" or "email"
        System.out.println("eBay OAuth2 attributes: " + oAuth2User.getAttributes());

        // Look up linked account
        LinkedAccount linkedAccount = linkedAccountRepository
                .findByProviderAndProviderUserId(provider, providerUserId)
                .orElseThrow(() -> new OAuth2AuthenticationException("No account linked with this OAuth provider"));

        User user = linkedAccount.getUser();

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                oAuth2User.getAttributes(),
                "sub"
        );
    }
}
