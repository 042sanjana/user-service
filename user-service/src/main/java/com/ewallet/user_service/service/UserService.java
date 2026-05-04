package com.ewallet.user_service.service;
import com.ewallet.user_service.config.RabbitMQConfig;
import com.ewallet.user_service.entity.UserProfile;
import com.ewallet.user_service.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserProfileRepository userProfileRepository;
    private final RabbitTemplate rabbitTemplate;

    public UserProfile createUser(UserProfile profile) {
        if (userProfileRepository.existsByEmail(profile.getEmail())) {
            throw new RuntimeException("Email already in use"+profile.getEmail());
        }
       UserProfile saved=userProfileRepository.save(profile);
        return saved;
    }

    public UserProfile getUser(Long id){
        return userProfileRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    public void deleteByEmail(String email){
        userProfileRepository.deleteByemail(email);
    }

    public UserProfile updateUser(Long id, String email, BigDecimal phoneNumber) {
        UserProfile user = userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found" + id));
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        return userProfileRepository.save(user);
    }

    public @Nullable Iterable<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    public void deleteUser(Long id) {
        userProfileRepository.deleteById(id);
    }


}