package com.mdg.sociallogintopaymentspring_bootjpa.service;

import com.mdg.sociallogintopaymentspring_bootjpa.dto.MemberDto;
import com.mdg.sociallogintopaymentspring_bootjpa.model.Authority;
import com.mdg.sociallogintopaymentspring_bootjpa.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberService memberService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.getMember(MemberDto.builder().id(username).build());

        Set<GrantedAuthority> set = new HashSet<>();
        set.add(new Authority());
        assert false;
        return new User(member.getId(), member.getPassword(), set);
    }
}
