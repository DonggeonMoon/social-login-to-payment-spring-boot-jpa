package com.mdg.sociallogintopaymentspring_bootjpa.dto;

import com.mdg.sociallogintopaymentspring_bootjpa.model.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class MemberDto {
    private String id;
    private String password;
    private String name;
    private LocalDate birthday;
    private String address;
    private String phone1;
    private String phone2;
    private String phone3;
    private String authority;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .password(password)
                .name(name)
                .birthday(birthday)
                .address(address)
                .phone1(phone1)
                .phone2(phone2)
                .phone3(phone3)
                .authority(authority)
                .build();
    }
}
