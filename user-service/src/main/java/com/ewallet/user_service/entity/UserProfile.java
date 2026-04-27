
package com.ewallet.user_service.entity;
import jakarta.persistence.*;
        import lombok.*;
        import java.time.LocalDateTime;


@Entity
@Table(name="user_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String fullName;

    @Column(updatable = false )
    private LocalDateTime createdAt;
@PrePersist
public void prePersist() {
    this.createdAt = LocalDateTime.now();
}
}