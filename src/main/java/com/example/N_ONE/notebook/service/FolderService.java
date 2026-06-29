package com.example.N_ONE.notebook.service;

import com.example.N_ONE.notebook.entity.Folder;
import com.example.N_ONE.notebook.entity.User;
import com.example.N_ONE.notebook.repository.FolderRepository;
import com.example.N_ONE.notebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    private final UserRepository userRepository;

    private User getAuthenticatedUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email).orElseThrow();
    }

    public Folder createFolder(Folder folderRequest) {
        User user = getAuthenticatedUser();
        Folder folder = Folder.builder()
                .name(folderRequest.getName())
                .user(user)
                .build();
        return folderRepository.save(folder);
    }

    public List<Folder> getAllFolders() {
        return folderRepository.findByUser(getAuthenticatedUser());
    }
}