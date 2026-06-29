package com.example.N_ONE.notebook.repository;

import com.example.N_ONE.notebook.entity.Folder;
import com.example.N_ONE.notebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FolderRepository extends JpaRepository<Folder, UUID> {
    List<Folder> findByUser(User user);
    Optional<Folder> findByIdAndUser(UUID id, User user);
}