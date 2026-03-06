package com.example.wake_up2.domain.user.dto;

import lombok.Getter;

@Getter
public class Signuprequest {
    private String name;
    private String email;
    private String password;
    private String code;
}
