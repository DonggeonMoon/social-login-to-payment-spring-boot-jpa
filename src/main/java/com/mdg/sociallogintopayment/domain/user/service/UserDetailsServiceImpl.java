package com.mdg.sociallogintopayment.domain.user.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mdg.sociallogintopayment.domain.user.dto.UserDto;
import com.mdg.sociallogintopayment.domain.user.model.Authority;
import com.mdg.sociallogintopayment.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.getUser(UserDto.builder().memberId(username).build());
            Set<GrantedAuthority> set = new HashSet<>();
            set.add(new Authority());

            return new org.springframework.security.core.userdetails.User(user.getMemberId(), user.getPassword(), set);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
