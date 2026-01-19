package com.ansimue.nht.controller;

import com.ansimue.nht.dto.CommentaireDTO;
import com.ansimue.nht.entities.CommentaireEntity;
import com.ansimue.nht.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;

    @PostMapping("/add")
    public ResponseEntity<CommentaireEntity> addComment(@RequestBody CommentaireDTO commentaireDTO) {
        return ResponseEntity.ok(commentaireService.addComment(commentaireDTO));
    }

    @GetMapping("/story/{storyId}")
    public ResponseEntity<List<CommentaireEntity>> getCommentsForStory(@PathVariable Long storyId) {
        return ResponseEntity.ok(commentaireService.getCommentsForStory(storyId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentaireService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
