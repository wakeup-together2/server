package com.example.wake_up2.domain.user.service;

import com.example.wake_up2.domain.user.repository.Userrepository;
import com.example.wake_up2.domain.user.repository.Verifyemail;
import com.example.wake_up2.domain.user.entity.User;
import com.example.wake_up2.domain.user.entity.Email;
import com.example.wake_up2.domain.user.dto.Loginrequest;
import com.example.wake_up2.domain.user.dto.Signuprequest;
import com.example.wake_up2.domain.user.dto.Authresponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final Userrepository userrepository;
    private final Verifyemail verifyemail;
    private final PasswordEncoder passwordEncoder;

}
