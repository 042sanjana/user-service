package com.ewallet.user_service.controller;

import com.ewallet.user_service.entity.UserProfile;
import com.ewallet.user_service.repository.UserProfileRepository;
import com.ewallet.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long userId) {
        Optional<UserProfile> user = userProfileRepository.findByUserId(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UserProfile> updateUser(@PathVariable Long id,@RequestBody UserProfile profile){
        return ResponseEntity.ok(userService.updateUser(id, profile.getEmail(),profile.getPhoneNumber()));
    }

    @GetMapping("/me")
    public ResponseEntity<String> whoAMI(
            @RequestHeader("X-User-Id") String userId){
        return ResponseEntity.ok("Logged in as :"+userId);
    }
}
