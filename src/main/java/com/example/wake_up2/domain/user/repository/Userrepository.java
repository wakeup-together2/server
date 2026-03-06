package com.example.wake_up2.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.wake_up2.domain.user.entity.User;
import java.util.Optional;

public interface Userrepository extends JpaRepository<User, Long> {
    Optional<User> findByemail(String email);
}
