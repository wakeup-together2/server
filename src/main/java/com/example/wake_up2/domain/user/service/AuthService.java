package com.example.wake_up2.domain.user.service;

import com.example.wake_up2.domain.user.repository.Userrepository;
import com.example.wake_up2.domain.user.repository.Verifyemail;
import com.example.wake_up2.domain.user.entity.User;
import com.example.wake_up2.domain.user.entity.Email;
import com.example.wake_up2.domain.user.dto.Loginrequest;
import com.example.wake_up2.domain.user.dto.Signuprequest;
import com.example.wake_up2.domain.user.dto.Authresponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final Userrepository userrepository;
    private final Verifyemail verifyemail;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Authresponse login(Loginrequest loginrequest, HttpSession session) {
        User user = userrepository.findByName(loginrequest.getName())
                .orElseThrow(() -> new IllegalArgumentException("이름 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(loginrequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이름 또는 비밀번호가 올바르지 않습니다.");
        }

        session.setAttribute("userID", user.getId());
        session.setAttribute("UserName", user.getName());

        return Authresponse.builder()
                .user_id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public void signup(Signuprequest signuprequest) {
        if (userrepository.findByEmail(signuprequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        if (userrepository.findByName(signuprequest.getName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이름입니다.");
        }

        Email verification = verifyemail
                .findTopByemailOrderByIdDesc(signuprequest.getEmail())
                .orElseThrow(()->new IllegalArgumentException("이메일 인증을 먼저 완료해 주세요"));

        if(!verification.getVerified()) {
            throw new IllegalArgumentException("이메일 인증이 완료되지 않았습니다.");
        }

        User user = User.builder()
                .email(signuprequest.getEmail())
                .name(signuprequest.getName())
                .password(passwordEncoder.encode(signuprequest.getPassword()))
                .build();
        userrepository.save(user);

        verifyemail.deleteByEmail(signuprequest.getEmail());
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
