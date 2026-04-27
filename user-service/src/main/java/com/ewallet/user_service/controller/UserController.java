package com.ewallet.user_service.controller;

import com.ewallet.user_service.entity.UserProfile;
import com.ewallet.user_service.repository.UserProfileRepository;
import com.ewallet.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserProfile profile) {
        if (userProfileRepository.existsByEmail(profile.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already in use");
        }
        return ResponseEntity.ok(userService.createUser(profile));
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


    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestHeader(value = "X-User-Role", required = false) String role) {
        if (role == null || !role.equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(403).body("Admin Access only");
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserProfile> updateUser(@PathVariable Long id,@RequestBody UserProfile profile){
        return ResponseEntity.ok(userService.updateUser(id, profile.getEmail()));
    }

    @GetMapping("/me")
    public ResponseEntity<String> whoAMI(
            @RequestHeader("X-User-Id") String userId){
        return ResponseEntity.ok("Logged in as :"+userId);
    }
}
