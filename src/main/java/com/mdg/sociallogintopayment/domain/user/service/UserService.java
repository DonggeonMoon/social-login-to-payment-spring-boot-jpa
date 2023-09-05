package com.mdg.sociallogintopayment.domain.user.service;

import com.mdg.sociallogintopayment.domain.user.dto.UserDto;
import com.mdg.sociallogintopayment.domain.user.model.User;
import com.mdg.sociallogintopayment.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        user.update(userDto.getMemberId(), userDto.getPassword(), userDto.getName(), userDto.getBirthday(), userDto.getAddress(), userDto.getPhoneNumber(), userDto.getEmail(),
                userDto.getUserAuthorities());
    }

    @Transactional
    public void deleteUser(UserDto userDto) {
        userRepository.deleteById(userDto.getId());
    }
}
