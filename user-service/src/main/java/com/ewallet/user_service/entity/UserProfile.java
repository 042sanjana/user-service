
package com.ewallet.user_service.entity;
import jakarta.persistence.*;
        import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


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
    private Long userId;
    private String email;
    private String fullName;
    private BigDecimal phoneNumber;
    private Date dateOfBirth;

    @Column(updatable = false )
    private LocalDateTime createdAt;
@PrePersist
public void prePersist() {
    this.createdAt = LocalDateTime.now();
}
}