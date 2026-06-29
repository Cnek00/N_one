package com.example.N_ONE.notebook.service;

import com.example.N_ONE.notebook.entity.Folder;
import com.example.N_ONE.notebook.entity.Note;
import com.example.N_ONE.notebook.entity.User;
import com.example.N_ONE.notebook.repository.FolderRepository;
import com.example.N_ONE.notebook.repository.NoteRepository;
import com.example.N_ONE.notebook.repository.TagRepository;
import com.example.N_ONE.notebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    private User getAuthenticatedUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Aktif kullanıcı bulunamadı!"));
    }

    // NoteService.java içerisine eklenecek/güncellenecek alanlar:
    private final FolderRepository folderRepository;
    private final TagRepository tagRepository;

    public Note createNote(Note noteRequest, UUID folderId, Set<UUID> tagIds) {
        User user = getAuthenticatedUser();
        
        Folder folder = null;
        if (folderId != null) {
            folder = folderRepository.findByIdAndUser(folderId, user)
                    .orElseThrow(() -> new RuntimeException("Geçersiz klasör veya bu klasör size ait değil!"));
        }

        Set<Tag> tags = null;
        if (tagIds != null && !tagIds.isEmpty()) {
            // Kullanıcının kendi etiketlerini doğrula
            // Akışı bozmamak için basitçe repository'den çekiyoruz, hatayı sonra profesyonelleştireceğiz kanka
            tags = new java.util.HashSet<>(tagRepository.findAllById(tagIds).stream()
                    .filter(tag -> tag.getUser().getId().equals(user.getId()))
                    .toList());
        }

        Note note = Note.builder()
                .title(noteRequest.getTitle())
                .content(noteRequest.getContent())
                .user(user)
                .folder(folder)
                .tags(tags)
                .build();
                
        return noteRepository.save(note);
    }

    // Güncellendi: Artık sadece silinmemiş aktif notları döner
    public List<Note> getAllActiveNotesOfUser() {
        User user = getAuthenticatedUser();
        return noteRepository.findByUserAndDeletedFalse(user);
    }

    // Yeni: Çöp kutusundaki notları listeler
    public List<Note> getTrashBinNotesOfUser() {
        User user = getAuthenticatedUser();
        return noteRepository.findByUserAndDeletedTrue(user);
    }

    // Güncellendi: Sadece aktif notlar güncellenebilir
    public Note updateNote(UUID noteId, Note noteRequest) {
        User user = getAuthenticatedUser();
        Note existingNote = noteRepository.findByIdAndUserAndDeletedFalse(noteId, user)
                .orElseThrow(() -> new RuntimeException("Aktif not bulunamadı veya yetkiniz yok kanka!"));

        existingNote.setTitle(noteRequest.getTitle());
        existingNote.setContent(noteRequest.getContent());
        return noteRepository.save(existingNote);
    }

    // Güncellendi: Yumuşak Silme (Soft Delete) - Notu çöp kutusuna atar
    public void softDeleteNote(UUID noteId) {
        User user = getAuthenticatedUser();
        Note existingNote = noteRepository.findByIdAndUserAndDeletedFalse(noteId, user)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı veya silme yetkiniz yok!"));

        existingNote.setDeleted(true);
        noteRepository.save(existingNote);
    }

    // Yeni: Çöp kutusundan geri kurtarma (Restore)
    public Note restoreNoteFromTrash(UUID noteId) {
        User user = getAuthenticatedUser();
        Note trashedNote = noteRepository.findByIdAndUser(noteId, user)
                .orElseThrow(() -> new RuntimeException("Çöp kutusunda böyle bir not bulunamadı!"));

        if (!trashedNote.isDeleted()) {
            throw new RuntimeException("Bu not zaten çöp kutusunda değil kanka!");
        }

        trashedNote.setDeleted(false);
        return noteRepository.save(trashedNote);
    }

    // Yeni: Kalıcı Silme (Hard Delete) - Veritabanından tamamen uçurur
    public void hardDeleteNote(UUID noteId) {
        User user = getAuthenticatedUser();
        Note trashedNote = noteRepository.findByIdAndUser(noteId, user)
                .orElseThrow(() -> new RuntimeException("Not bulunamadı veya silme yetkiniz yok!"));

        noteRepository.delete(trashedNote);
    }
    // NoteService.java sınıfının içine eklenecek arama metodu kanka:
    
    public List<Note> searchNotes(String keyword, UUID folderId, Set<UUID> tagIds) {
        User user = getAuthenticatedUser(); // İstek atan kullanıcıyı çek
        
        // Specification motorunu tetikle
        org.springframework.data.jpa.domain.Specification<Note> spec = 
                com.example.N_ONE.notebook.repository.specification.NoteSpecification.searchNotes(keyword, folderId, tagIds, user);
                
        return noteRepository.findAll(spec);
    }
}