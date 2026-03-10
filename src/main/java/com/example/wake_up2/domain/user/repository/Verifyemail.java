package com.example.wake_up2.domain.user.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.wake_up2.domain.user.entity.Email;
import java.util.Optional;

public interface Verifyemail extends JpaRepository<Email,Long> {
    Optional<Email> findTopByemailOrderByIdDesc(String email);
    void deleteByEmail(String email);
}