package com.example.N_ONE.notebook.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "google_sub_id", unique = true)
    private String googleSubId;

    @Column(name = "password_hash")
    private String passwordHash;

    // DÜZELTME: name = "created_at" kaldırıldı.
    // Hibernate naming strategy "createdAt" -> "created_at" dönüşümünü zaten yapıyor.
    // İkisini birden yazmak Hibernate 7'de DuplicateMappingException fırlatıyor.
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}