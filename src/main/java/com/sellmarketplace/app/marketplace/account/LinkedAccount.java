package com.sellmarketplace.app.marketplace.account;


import com.sellmarketplace.app.marketplace.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LinkedAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider; // e.g., "google", "github"
    private String providerUserId; // e.g., Google "sub"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
