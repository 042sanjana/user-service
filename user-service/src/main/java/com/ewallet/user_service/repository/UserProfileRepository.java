package com.ewallet.user_service.repository;

import com.ewallet.user_service.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    void deleteByemail(String email);
    boolean existsByEmail(String email);

    Optional<UserProfile>findByUserId(Long userId);
    Optional<UserProfile>findByEmail(String email);
}
