package com.mdg.sociallogintopayment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;

import com.mdg.sociallogintopayment.model.Authority;
import com.mdg.sociallogintopayment.model.User;
import com.mdg.sociallogintopayment.model.UserAuthority;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto implements UserDetails {
    private Long id;
    private String memberId;
    private String password;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String email;
    private List<UserAuthority> userAuthorities;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static UserDto of(Long id, String memberId, String password, String name, LocalDate birthday, String address, String phoneNumber, String email, List<UserAuthority> userAuthorities,
        LocalDateTime createdAt, LocalDateTime modifiedAt) {

        return UserDto.builder()
            .id(id)
            .memberId(memberId)
            .password(password)
            .name(name)
            .birthday(birthday)
            .address(address)
            .phoneNumber(phoneNumber)
            .email(email)
            .userAuthorities(userAuthorities)
            .createdAt(createdAt)
            .modifiedAt(modifiedAt)
            .build();
    }

    public User toEntity() {
        return User.of(memberId, password, name, birthday, address, phoneNumber, email, userAuthorities);
    }

    @Override
    public Collection<Authority> getAuthorities() {
        return userAuthorities.stream()
            .map(UserAuthority::getAuthority)
            .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
