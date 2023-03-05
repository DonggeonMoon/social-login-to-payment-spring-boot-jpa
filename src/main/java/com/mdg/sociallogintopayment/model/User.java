package com.mdg.sociallogintopayment.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.mdg.sociallogintopayment.dto.UserDto;
import com.mdg.sociallogintopayment.util.PersonalDateInformationConverter;
import com.mdg.sociallogintopayment.util.PersonalInformationConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
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

    public void update(long id, String memberId, String password, String name, LocalDate birthday, String address, String phoneNumber, String email, List<UserAuthority> userAuthorities) {
        this.id = id;
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
