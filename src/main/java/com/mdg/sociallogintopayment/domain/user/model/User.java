package com.mdg.sociallogintopayment.domain.user.model;

import com.mdg.sociallogintopayment.domain.user.dto.UserDto;
import com.mdg.sociallogintopayment.global.util.PersonalDateInformationConverter;
import com.mdg.sociallogintopayment.global.util.PersonalInformationConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String memberId;
    @Column(nullable = false)
    //@Convert(converter = PasswordConverter.class)
    private String password;
    @Column(nullable = false)
    @Convert(converter = PersonalInformationConverter.class)
    private String name;
    @Column(nullable = false)
    @Convert(converter = PersonalDateInformationConverter.class)
    private LocalDate birthday;
    @Convert(converter = PersonalInformationConverter.class)
    private String address;
    @Convert(converter = PersonalInformationConverter.class)
    private String phoneNumber;
    @Convert(converter = PersonalInformationConverter.class)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<UserAuthority> userAuthorities;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public static User of(String memberId, String password, String name, LocalDate birthday, String address, String phoneNumber, String email, List<UserAuthority> userAuthorities) {
        return User.builder()
                .memberId(memberId)
                .password(password)
                .name(name)
                .birthday(birthday)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .userAuthorities(userAuthorities)
                .build();
    }

    public void update(String memberId, String password, String name, LocalDate birthday, String address, String phoneNumber, String email, List<UserAuthority> userAuthorities) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userAuthorities = userAuthorities;
    }

    public UserDto toDto() {
        return UserDto.of(id, memberId, password, name, birthday, address, phoneNumber, email, userAuthorities, createdAt, modifiedAt);
    }
}
