package com.example.N_ONE.notebook.repository.specification;

import com.example.N_ONE.notebook.entity.Note;
import com.example.N_ONE.notebook.entity.User;
import com.example.N_ONE.notebook.entity.Tag;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class NoteSpecification {

    public static Specification<Note> searchNotes(String keyword, UUID folderId, Set<UUID> tagIds, User authenticatedUser) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. ZORUNLU KURAL: %100 Veri İzolasyonu (Sadece aktif kullanıcının notları)
            predicates.add(criteriaBuilder.equal(root.get("user"), authenticatedUser));

            // 2. ZORUNLU KURAL: Çöp kutusundaki notları arama sonuçlarına dahil etme
            predicates.add(criteriaBuilder.equal(root.get("deleted"), false));

            // 3. OPSİYONEL: Kelime Bazlı Arama (Başlıkta VEYA içerikte geçiyorsa - Case Insensitive)
            if (keyword != null && !keyword.trim().isEmpty()) {
                String lowerKeyword = "%" + keyword.toLowerCase() + "%";
                Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), lowerKeyword);
                Predicate contentPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("content")), lowerKeyword);
                predicates.add(criteriaBuilder.or(titlePredicate, contentPredicate));
            }

            // 4. OPSİYONEL: Klasör Filtresi
            if (folderId != null) {
                predicates.add(criteriaBuilder.equal(root.get("folder").get("id"), folderId));
            }

            // 5. OPSİYONEL: Etiket Filtresi (Çoka-Çok İlişki JOIN İşlemi)
            if (tagIds != null && !tagIds.isEmpty()) {
                Join<Note, Tag> tagJoin = root.join("tags");
                predicates.add(tagJoin.get("id").in(tagIds));
                // Çoka çok join işleminde çoklu satır dönmesini engellemek için distinct atıyoruz kanka
                query.distinct(true);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}