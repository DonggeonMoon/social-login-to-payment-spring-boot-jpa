package com.mdg.sociallogintopaymentspring_bootjpa.model;

import com.mdg.sociallogintopaymentspring_bootjpa.dto.MemberDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    private String id;
    private String password;
    private String name;
    private LocalDate birthday;
    private String address;
    private String phone1;
    private String phone2;
    private String phone3;
    private String authority;

    public MemberDto toDto() {
        return MemberDto.builder()
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

    public void update(String id, String password, String name, LocalDate birthday, String address, String phone1, String phone2, String phone3, String authority) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.authority = authority;
    }
}
