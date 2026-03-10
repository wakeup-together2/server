package com.example.wake_up2.global.security;

public final class SecurityConstants {

    private SecurityConstants() {} // 인스턴스 생성 방지

    public static final String[] PUBLIC_URLS = {
            "/api/auth/**"
    };
    public static final String LOGOUT_URL = "/api/auth/logout";
    public static final String SESSION_COOKIE = "JSESSIONID";
}