package com.ansimue.nht.controller;

import com.ansimue.nht.dto.ChapterDTO;
import com.ansimue.nht.entities.ChapterEntity;
import com.ansimue.nht.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@CrossOrigin(origins = "*")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @PostMapping("/add")
    public ResponseEntity<ChapterEntity> addChapter(@RequestBody ChapterDTO chapterDTO) {
        return ResponseEntity.ok(chapterService.addChapter(chapterDTO));
    }

    @GetMapping("/story/{storyId}")
    public ResponseEntity<List<ChapterEntity>> getChaptersByStory(@PathVariable Long storyId) {
        return ResponseEntity.ok(chapterService.getChaptersByStory(storyId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChapterEntity> updateChapter(@PathVariable Long id, @RequestBody ChapterDTO chapterDTO) {
        return ResponseEntity.ok(chapterService.updateChapter(id, chapterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        chapterService.deleteChapter(id);
        return ResponseEntity.ok().build();
    }
}
