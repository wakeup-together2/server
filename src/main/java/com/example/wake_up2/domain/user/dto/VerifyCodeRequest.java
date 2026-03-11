package com.example.wake_up2.domain.user.dto;

import lombok.Getter;

@Getter
public class VerifyCodeRequest {
    private String email;
    private String code;
}