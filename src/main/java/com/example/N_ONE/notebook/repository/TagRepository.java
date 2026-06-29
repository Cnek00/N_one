package com.example.N_ONE.notebook.repository;

import com.example.N_ONE.notebook.entity.Tag;
import com.example.N_ONE.notebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    List<Tag> findByUser(User user);
    Optional<Tag> findByIdAndUser(UUID id, User user);
}