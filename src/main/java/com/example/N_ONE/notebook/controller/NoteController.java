package com.example.N_ONE.notebook.controller;

import com.example.N_ONE.notebook.entity.Note;
import com.example.N_ONE.notebook.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    // NoteController içindeki createNote metodunu esnetiyoruz:
    @PostMapping
    public ResponseEntity<Note> createNote(
            @RequestBody Note note,
            @RequestParam(required = false) UUID folderId,
            @RequestParam(required = false) Set<UUID> tagIds) {
        return ResponseEntity.ok(noteService.createNote(note, folderId, tagIds));
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllActiveNotes() {
        return ResponseEntity.ok(noteService.getAllActiveNotesOfUser());
    }

    // 🗑️ Çöp Kutusu Listeleme
    @GetMapping("/trash")
    public ResponseEntity<List<Note>> getTrashBin() {
        return ResponseEntity.ok(noteService.getTrashBinNotesOfUser());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable UUID id, @RequestBody Note note) {
        return ResponseEntity.ok(noteService.updateNote(id, note));
    }

    // ↪️ Yumuşak Silme (Çöpe Atma)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteNote(@PathVariable UUID id) {
        noteService.softDeleteNote(id);
        return ResponseEntity.noContent().build();
    }

    // 🔄 Çöp Kutularından Geri Yükleme
    @PostMapping("/{id}/restore")
    public ResponseEntity<Note> restoreNote(@PathVariable UUID id) {
        return ResponseEntity.ok(noteService.restoreNoteFromTrash(id));
    }

    // 🛑 Kalıcı Olarak Silme (Hard Delete)
    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<Void> hardDeleteNote(@PathVariable UUID id) {
        noteService.hardDeleteNote(id);
        return ResponseEntity.noContent().build();
    }
    // NoteController.java sınıfının içerisine eklenecek endpoint:

    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotes(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) UUID folderId,
            @RequestParam(required = false) Set<UUID> tagIds) {
            
        List<Note> results = noteService.searchNotes(keyword, folderId, tagIds);
        return ResponseEntity.ok(results);
    }
}