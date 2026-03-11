package com.example.wake_up2.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.wake_up2.domain.user.entity.Email;
import com.example.wake_up2.domain.user.repository.Verifyemail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.Random;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final Verifyemail verifyemail;

    public void sendEmail(String email) {
        String code = String.format("%06d", new Random().nextInt(100000));

        Email verification = Email.builder()
                .email(email)
                .code(code)
                .expire_at(LocalDateTime.now().plusMinutes(5))
                .verified(false)
                .build();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("인증코드 :"+code+"\n\n\n5분안에 입력해주세요.");
        message.setTo(email);
        message.setSubject("Wake-up Email Verification");

        verifyemail.save(verification);
        mailSender.send(message);
    }
    public void verifyCode(String code, String email) {
        Email verification = verifyemail
                .findTopByemailOrderByIdDesc(email)
                .orElseThrow(()->new IllegalArgumentException("인증 코드를 먼저 요청해 주세요."));
        if (verification.isExpired()) {
            throw new IllegalArgumentException("인증코드가 만료되었습니다.");
        }

        if (!verification.getCode().equals(code)) {
            throw new IllegalArgumentException("인증 코드가 올바르지 않습니다.");
        }

        verification.verify();
        verifyemail.save(verification);
    }
}
