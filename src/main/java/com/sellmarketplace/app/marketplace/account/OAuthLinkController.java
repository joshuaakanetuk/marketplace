package com.sellmarketplace.app.marketplace.account;

import com.sellmarketplace.app.marketplace.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sellmarketplace.app.marketplace.user.User;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/account")
public class OAuthLinkController {

    private final UserRepository userRepository;
    private final LinkedAccountRepository linkedAccountRepository;

    public OAuthLinkController(UserRepository userRepository, LinkedAccountRepository linkedAccountRepository) {
        this.userRepository = userRepository;
        this.linkedAccountRepository = linkedAccountRepository;
    }

    @PostMapping("/link-oauth")
    public String linkOAuthAccount(@AuthenticationPrincipal UserDetails userDetails, OAuth2AuthenticationToken token) {
        OAuth2User oAuth2User = token.getPrincipal();
        String provider = token.getAuthorizedClientRegistrationId(); // e.g., "google"
        String providerUserId = oAuth2User.getAttribute("sub");      // or "id" for GitHub, depends on provider

        // 1. Get logged-in user from local DB
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // 2. Check if provider is already linked
        boolean alreadyLinked = user.getLinkedAccounts().stream()
                .anyMatch(acc -> acc.getProvider().equals(provider) && acc.getProviderUserId().equals(providerUserId));

        // 3. Link if not already linked
        if (!alreadyLinked) {
            LinkedAccount linkedAccount = new LinkedAccount();
            linkedAccount.setProvider(provider);
            linkedAccount.setProviderUserId(providerUserId);
            linkedAccount.setUser(user);
            linkedAccountRepository.save(linkedAccount);
        }

        return "redirect:/profile?linked=true";
    }
}
