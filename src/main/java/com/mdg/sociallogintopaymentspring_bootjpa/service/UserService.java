package com.mdg.sociallogintopaymentspring_bootjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdg.sociallogintopaymentspring_bootjpa.dto.UserDto;
import com.mdg.sociallogintopaymentspring_bootjpa.model.User;
import com.mdg.sociallogintopaymentspring_bootjpa.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void insertUser(UserDto userDto) {
        userRepository.save(userDto.toEntity());
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User getUser(UserDto userDto) throws Exception {
        return userRepository.findById(userDto.getId()).orElseThrow(Exception::new);
    }

    @Transactional
    public void updateUser(UserDto userDto) throws Exception {
        User user = userRepository.findById(userDto.getId()).orElseThrow(Exception::new);
        user.update(userDto.getId(), userDto.getMemberId(), userDto.getPassword(), userDto.getName(), userDto.getBirthday(), userDto.getAddress(), userDto.getPhoneNumber(), userDto.getEmail(),
            userDto.getAuthority());
    }
}
