package com.mdg.sociallogintopayment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdg.sociallogintopayment.dto.UserDto;
import com.mdg.sociallogintopayment.model.User;
import com.mdg.sociallogintopayment.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUser(UserDto userDto) throws Exception {
        return userRepository.findByMemberId(userDto.getMemberId()).orElseThrow(Exception::new);
    }

    @Transactional
    public void insertUser(UserDto userDto) {
        userRepository.save(userDto.toEntity());
    }

    @Transactional
    public void updateUser(UserDto userDto) throws Exception {
        User user = userRepository.findByMemberId(userDto.getMemberId()).orElseThrow(Exception::new);
        user.update(userDto.getId(), userDto.getMemberId(), userDto.getPassword(), userDto.getName(), userDto.getBirthday(), userDto.getAddress(), userDto.getPhoneNumber(), userDto.getEmail(),
            userDto.getUserAuthorities());
    }

    @Transactional
    public void deleteUser(UserDto userDto) {
        userRepository.deleteById(userDto.getId());
    }
}
