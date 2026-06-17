package com.ewallet.user_service.controller;

import com.ewallet.user_service.entity.UserProfile;
import com.ewallet.user_service.repository.UserProfileRepository;
import com.ewallet.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")

@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public ResponseEntity<?> home(){
        return ResponseEntity.ok("User Service is running");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader("X-User-Id")Long userId) {
        Optional<UserProfile> user = userProfileRepository.findByUserId(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    @PostMapping("/{id}")
    public ResponseEntity<UserProfile> updateUser(@PathVariable Long id,@RequestBody UserProfile profile){
        return ResponseEntity.ok(userService.updateUser(id, profile.getEmail(),profile.getPhoneNumber()));
    }


}
