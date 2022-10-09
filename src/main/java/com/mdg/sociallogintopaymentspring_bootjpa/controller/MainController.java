package com.mdg.sociallogintopaymentspring_bootjpa.controller;

import com.mdg.sociallogintopaymentspring_bootjpa.dto.MemberDto;
import com.mdg.sociallogintopaymentspring_bootjpa.model.Member;
import com.mdg.sociallogintopaymentspring_bootjpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberService memberService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/")
    public String main() {
        return "test";
    }

    @PostMapping("/loginProcess")
    public String processLogin(@ModelAttribute MemberDto memberDto) {
        System.out.println("로그인 중");
        System.out.println("memberDto.getId() = " + memberDto.getId());
        System.out.println("memberDto.getPassword() = " + memberDto.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(memberDto.getId());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails,
                memberDto.getPassword(),
                userDetails.getAuthorities()
        );
        System.out.println("여기");
        authenticationManager.authenticate(token);
        System.out.println("여기2");
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);

            return "redirect:/loginSuccess";
        }
        System.out.println("로그인 실패");
        return "redirect:/loginFailure";
    }

    @GetMapping("/loginSuccess")
    @ResponseBody
    public String afterAuthenticated() {
        return "Login Succeeded!";
    }

    @GetMapping("/loginFailure")
    @ResponseBody
    public String authenticationFailed() {
        return "Login Failed!";
    }

    @GetMapping("/join")
    public String join(@ModelAttribute MemberDto memberDto) {
        return null;
    }

    @PostMapping("/join")
    public String joinPost(@ModelAttribute MemberDto memberDto) {
        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        memberService.insertMember(memberDto);
        return "redirect:/";
    }

    @GetMapping("/list")
    @ResponseBody
    public String list() {
        String result = "";
        List<Member> memberList = memberService.getMemberList();
        for (Member member : memberList) {
            result += member.toString();
        }
        return result;
    }
}
