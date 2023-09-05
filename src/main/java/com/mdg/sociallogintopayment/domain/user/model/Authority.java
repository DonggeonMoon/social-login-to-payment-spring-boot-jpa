package com.mdg.sociallogintopayment.domain.user.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authority implements GrantedAuthority {
    @Id
    private Long id;
    private String roleName;
    @OneToMany(mappedBy = "authority")
    private List<UserAuthority> userAuthorities;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
