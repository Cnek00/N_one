package com.example.N_ONE.notebook.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.example.N_ONE.notebook.entity.User; // Farklı pakette olduğu için import ettik kanka

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // --- ADM 1: Kullanıcı İlişkisi Eklendi ---
    @ManyToOne(fetch = FetchType.LAZY) // Performans için LAZY loading tercih ettik
    @JoinColumn(name = "user_id", nullable = false) // Veritabanında user_id kolonu oluşacak ve boş geçilemeyecek
    private User user;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Getter ve Setter Metotları ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Yeni eklenen User için Getter ve Setter
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}