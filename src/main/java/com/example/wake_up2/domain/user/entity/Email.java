package com.example.wake_up2.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_verification")

public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expire_at;

    @Builder.Default
    @Column(nullable = false)
    private Boolean verified = false;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expire_at);
    }

    public void verify() {
        this.verified = true;
    }
}
