package com.mdg.sociallogintopayment.domain.user.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "authority id")
    private Authority authority;
}
