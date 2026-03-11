package com.example.wake_up2.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.example.wake_up2.domain.user.dto.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.wake_up2.domain.user.service.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final EmailService emailService;

    @PostMapping("/login")
    public Authresponse login(@RequestBody Loginrequest loginrequest, HttpSession session) {
        return authService.login(loginrequest,session);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody Signuprequest signuprequest) {
        authService.signup(signuprequest);
        return "회원가입 성공";
    }

    @PostMapping("/send-code")
    public String sendCode(@RequestBody SendCodeRequest sendCodeRequest) {
        emailService.sendEmail(sendCodeRequest.getEmail());
        return "인증 코드가 전송되었습니다.";
    }

    @PostMapping("/verify-code")
    public String verifyCode(@RequestBody VerifyCodeRequest verifyCodeRequest) {
        emailService.verifyCode(verifyCodeRequest.getEmail(), verifyCodeRequest.getCode());
        return "인증이 완료되었습니다.";
    }
}
