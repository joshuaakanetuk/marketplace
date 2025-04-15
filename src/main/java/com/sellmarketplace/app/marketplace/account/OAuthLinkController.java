package com.sellmarketplace.app.marketplace.account;

import com.sellmarketplace.app.marketplace.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sellmarketplace.app.marketplace.user.User;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Controller
public class OAuthLinkController {

    private final UserRepository userRepository;
    private final LinkedAccountRepository linkedAccountRepository;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    public OAuthLinkController(UserRepository userRepository, LinkedAccountRepository linkedAccountRepository) {
        this.userRepository = userRepository;
        this.linkedAccountRepository = linkedAccountRepository;
    }

    @GetMapping("/login/oauth2/code/ebay")
    public String handleEbayRedirect(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        if (code == null || state == null) {
            return "redirect:/login?error=ebay_oauth_error";
        }

        // Redirect to the standard OAuth2 endpoint that Spring Security handles
        return "redirect:/login/oauth2/code/ebay?code=" + code + "&state=" + state;
    }

    @GetMapping("/login/ebay")
    public String initiateEbayLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ClientRegistration ebayRegistration = clientRegistrationRepository.findByRegistrationId("ebay");

        // Generate state parameter for CSRF protection
        String state = UUID.randomUUID().toString();
        request.getSession().setAttribute("OAUTH2_STATE", state);

        String authorizationUri = UriComponentsBuilder
                .fromUriString(ebayRegistration.getProviderDetails().getAuthorizationUri())
                .queryParam("client_id", ebayRegistration.getClientId())
                .queryParam("response_type", "code")
                .queryParam("redirect_uri", ebayRegistration.getRedirectUri())
                .queryParam("scope", String.join(" ", ebayRegistration.getScopes()))
                .queryParam("state", state)
                .build().toUriString();

        return "redirect:" + authorizationUri;
    }


//    @PostMapping("/link-oauth")
//    public String linkOAuthAccount(@AuthenticationPrincipal UserDetails userDetails, OAuth2AuthenticationToken token) {
//        OAuth2User oAuth2User = token.getPrincipal();
//        String provider = token.getAuthorizedClientRegistrationId(); // e.g., "google"
//        String providerUserId = oAuth2User.getAttribute("username");      // or "id" for GitHub, depends on provider
//
//
//        System.out.println("eBay OAuth2 attributes: " + oAuth2User.getAttributes());
//
//        // 1. Get logged-in user from local DB
//        User user = userRepository.findByEmail(userDetails.getUsername())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//
//        // 2. Check if provider is already linked
//        boolean alreadyLinked = user.getLinkedAccounts().stream()
//                .anyMatch(acc -> acc.getProvider().equals(provider) && acc.getProviderUserId().equals(providerUserId));
//
//        // 3. Link if not already linked
//        if (!alreadyLinked) {
//            LinkedAccount linkedAccount = new LinkedAccount();
//            linkedAccount.setProvider(provider);
//            linkedAccount.setProviderUserId(providerUserId);
//            linkedAccount.setUser(user);
//            linkedAccountRepository.save(linkedAccount);
//        }
//
//        return "redirect:/profile?linked=true";
//    }

}
