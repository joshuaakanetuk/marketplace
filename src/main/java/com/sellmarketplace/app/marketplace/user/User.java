package com.sellmarketplace.app.marketplace.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password; // hashed

    private String name;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<LinkedAccount> linkedAccounts = new ArrayList<>();
}
