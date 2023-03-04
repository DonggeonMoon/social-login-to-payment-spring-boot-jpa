package com.mdg.sociallogintopaymentspring_bootjpa.controller;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mdg.sociallogintopaymentspring_bootjpa.dto.UserDto;
import com.mdg.sociallogintopaymentspring_bootjpa.model.User;
import com.mdg.sociallogintopaymentspring_bootjpa.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/")
    public String main() {
        return "test";
    }

    @PostMapping("/loginProcess")
    public String processLogin(@RequestParam String id, @RequestParam String password) throws AuthenticationException {
        System.out.println("로그인 중");
        System.out.println("id = " + id);
        System.out.println("password = " + password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(id);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            userDetails,
            password,
            userDetails.getAuthorities()
        );
        try {
            authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            return "redirect:/loginFailure";
        }
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
    public String join(@ModelAttribute UserDto userDto) {
        return null;
    }

    @PostMapping("/join")
    public String joinPost(@ModelAttribute UserDto userDto) {
        userService.insertUser(userDto);
        return "redirect:/";
    }

    @GetMapping("/list")
    @ResponseBody
    public String list() {
        StringBuilder result = new StringBuilder();
        List<User> userList = userService.getUserList();
        for (User user : userList) {
            result.append("\n").append(user.toString());
        }
        return result.toString();
    }
}
