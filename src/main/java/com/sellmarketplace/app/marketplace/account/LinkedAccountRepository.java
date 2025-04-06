package com.sellmarketplace.app.marketplace.account;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LinkedAccountRepository extends JpaRepository<LinkedAccount, Long> {
    Optional<LinkedAccount> findByProviderAndProviderUserId(String provider, String providerUserId);
}
