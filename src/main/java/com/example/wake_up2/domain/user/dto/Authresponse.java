package com.example.wake_up2.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Authresponse {
    private String email;
    private Long user_id;
    private String name;
}
