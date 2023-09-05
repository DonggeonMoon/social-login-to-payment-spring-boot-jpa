package com.mdg.sociallogintopayment.domain.user.service;

import com.mdg.sociallogintopayment.domain.user.model.User;
import com.mdg.sociallogintopayment.domain.user.model.UserAuthority;
import com.mdg.sociallogintopayment.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private final String userId = "abc";
    private final String newUserId = "abc1";
    private final String name = "김민수";
    private final String password = "1234";
    private final LocalDate birthday = LocalDate.of(1990, 1, 1);
    private final String email = "abc@naver.com";
    private final String address = "서울";
    private final String phoneNumber = "010-0000-0000";

    private final User newUser = User.builder()
            .memberId(newUserId)
            .password(password)
            .name(name)
            .birthday(birthday)
            .email(email)
            .address(address)
            .phoneNumber(phoneNumber)
            .build();
    @Mock
    UserRepository userRepository;

    @Test
    void getUsers() {
        userRepository.findAll();
    }

    @Test
    void getUser() {
        userRepository.findByMemberId(userId);
    }

    @Test
    void insertUser() {
        userRepository.save(newUser);
    }

    @Test
    void updateUser() {
        User user = userRepository.findByMemberId(newUserId).orElseThrow(RuntimeException::new);
        user.update(newUser.getMemberId(),
                password,
                name,
                birthday,
                address,
                phoneNumber,
                email,
                List.of(new UserAuthority())
        );
    }

    @Test
    void deleteUser() {
        userRepository.deleteByMemberId(
                newUser.getMemberId()
        );
    }
}