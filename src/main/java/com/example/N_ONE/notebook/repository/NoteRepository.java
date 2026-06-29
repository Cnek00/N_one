package com.example.N_ONE.notebook.repository;

import com.example.N_ONE.notebook.entity.Note;
import com.example.N_ONE.notebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID>, JpaSpecificationExecutor<Note> {
    
    List<Note> findByUserAndDeletedFalse(User user);
    List<Note> findByUserAndDeletedTrue(User user);
    Optional<Note> findByIdAndUserAndDeletedFalse(UUID id, User user);
    Optional<Note> findByIdAndUser(UUID id, User user);
}