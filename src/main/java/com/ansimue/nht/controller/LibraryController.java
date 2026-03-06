package com.ansimue.nht.controller;

import com.ansimue.nht.dto.LibraryDTO;
import com.ansimue.nht.entities.LibraryEntity;
import com.ansimue.nht.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/add")
    public ResponseEntity<LibraryEntity> addToLibrary(@RequestBody LibraryDTO libraryDTO) {
        return ResponseEntity.ok(libraryService.addToLibrary(libraryDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LibraryEntity>> getUserLibrary(@PathVariable Long userId) {
        return ResponseEntity.ok(libraryService.getUserLibrary(userId));
    }

    @PutMapping("/update-progress")
    public ResponseEntity<LibraryEntity> updateProgress(
            @RequestParam Long userId,
            @RequestParam Long storyId,
            @RequestParam Integer chapterNumber) {
        return ResponseEntity.ok(libraryService.updateProgress(userId, storyId, chapterNumber));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFromLibrary(
            @RequestParam Long userId,
            @RequestParam Long storyId) {
        libraryService.removeFromLibrary(userId, storyId);
        return ResponseEntity.ok().build();
    }
}
