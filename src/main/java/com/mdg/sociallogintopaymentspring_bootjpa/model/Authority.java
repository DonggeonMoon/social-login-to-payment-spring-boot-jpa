package com.mdg.sociallogintopaymentspring_bootjpa.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

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
