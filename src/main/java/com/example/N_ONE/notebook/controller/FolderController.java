package com.example.N_ONE.notebook.controller;

import com.example.N_ONE.notebook.entity.Folder;
import com.example.N_ONE.notebook.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/folders")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping
    public ResponseEntity<Folder> createFolder(@RequestBody Folder folder) {
        return ResponseEntity.ok(folderService.createFolder(folder));
    }

    @GetMapping
    public ResponseEntity<List<Folder>> getAllFolders() {
        return ResponseEntity.ok(folderService.getAllFolders());
    }
}