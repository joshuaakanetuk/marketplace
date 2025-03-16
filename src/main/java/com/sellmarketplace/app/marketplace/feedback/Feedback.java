package com.sellmarketplace.app.marketplace.feedback;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String feedback;

    @Column
    private Boolean isNegative;
}
