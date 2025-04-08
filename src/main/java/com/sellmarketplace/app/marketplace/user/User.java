package com.sellmarketplace.app.marketplace.user;

import com.sellmarketplace.app.marketplace.account.LinkedAccount;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users") // Avoid reserved keyword
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password; // hashed

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LinkedAccount> linkedAccounts = new ArrayList<>();
}
