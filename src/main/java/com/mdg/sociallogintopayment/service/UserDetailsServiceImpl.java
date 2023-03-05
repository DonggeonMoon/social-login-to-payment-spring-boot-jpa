package com.mdg.sociallogintopayment.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mdg.sociallogintopayment.dto.UserDto;
import com.mdg.sociallogintopayment.model.Authority;
import com.mdg.sociallogintopayment.model.User;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(UserDto.builder().id(username).build());

        Set<GrantedAuthority> set = new HashSet<>();
        set.add(new Authority());
        assert false;
        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), set);
    }
}
