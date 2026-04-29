
package com.ewallet.user_service.listener;

import com.ewallet.user_service.config.RabbitMQConfig;
import com.ewallet.user_service.entity.UserProfile;
import com.ewallet.user_service.event.UserEvent;
import com.ewallet.user_service.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRegisteredListener {

    private final UserProfileRepository userProfileRepository;

    @RabbitListener(queues = RabbitMQConfig.USER_REGISTERED_QUEUE)
    public void onUserRegistered(UserEvent event) {
        log.info("User Service received event for userId: {}", event.getUserId());

        if (userProfileRepository.existsByEmail(event.getEmail())) {
            log.warn("User with email {} already exists, skipping event", event.getEmail());
            return;
        }
        UserProfile userProfile=UserProfile.builder().
                userId(event.getUserId()).
                email(event.getEmail()).
                fullName(event.getFullName()).
                phoneNumber(event.getPhoneNumber()).
                dateOfBirth(event.getDateOfBirth()).build();
        userProfileRepository.save(userProfile);
        System.out.println("user profile saved");
    }
}