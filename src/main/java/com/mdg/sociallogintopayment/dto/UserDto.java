package com.mdg.sociallogintopayment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.mdg.sociallogintopayment.model.User;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
    private String id;
    private String memberId;
    private String password;
    private String name;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String email;
    private String authority;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public User toEntity() {
        return User.builder()
            .memberId(memberId)
            .id(id)
            .password(password)
            .name(name)
            .birthday(birthday)
            .address(address)
            .phoneNumber(phoneNumber)
            .email(email)
            .authority(authority)
            .createdAt(createdAt)
            .modifiedAt(modifiedAt)
            .build();
    }

}
