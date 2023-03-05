package com.mdg.sociallogintopayment.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authority implements GrantedAuthority {
    @Id
    private Long id;
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
