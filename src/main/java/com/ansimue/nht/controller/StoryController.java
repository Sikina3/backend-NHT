package com.ansimue.nht.controller;

import com.ansimue.nht.dto.StoryDTO;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@CrossOrigin(origins = "*")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<StoryEntity> createStory(
            @ModelAttribute StoryDTO storyDTO,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.ok(storyService.createStory(storyDTO, image));
    }

    @GetMapping
    public ResponseEntity<List<StoryEntity>> getAllStories() {
        return ResponseEntity.ok(storyService.getAllStories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoryEntity> getStoryById(@PathVariable Long id) {
        return storyService.getStoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<StoryEntity> updateStory(
            @PathVariable Long id,
            @ModelAttribute StoryDTO storyDTO,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.ok(storyService.updateStory(id, storyDTO, image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {
        storyService.deleteStory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StoryEntity>> searchStories(@RequestParam String query) {
        return ResponseEntity.ok(storyService.searchStories(query));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<StoryEntity>> getStoriesByCategory(
            @PathVariable com.ansimue.nht.model.Category category) {
        return ResponseEntity.ok(storyService.getStoriesByCategory(category));
    }

    @PostMapping("/{id}/view")
    public ResponseEntity<Void> incrementVues(@PathVariable Long id) {
        storyService.incrementVues(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> incrementLikes(@PathVariable Long id) {
        storyService.incrementLikes(id);
        return ResponseEntity.ok().build();
    }
}
