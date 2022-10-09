package com.mdg.sociallogintopaymentspring_bootjpa.dto;

import com.mdg.sociallogintopaymentspring_bootjpa.model.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return Objects.equals(id, memberDto.id) && Objects.equals(password, memberDto.password) && Objects.equals(name, memberDto.name) && Objects.equals(birthday, memberDto.birthday) && Objects.equals(address, memberDto.address) && Objects.equals(phone1, memberDto.phone1) && Objects.equals(phone2, memberDto.phone2) && Objects.equals(phone3, memberDto.phone3) && Objects.equals(authority, memberDto.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, name, birthday, address, phone1, phone2, phone3, authority);
    }
}
