package com.ansimue.nht.controller;

import com.ansimue.nht.dto.ReactionDTO;
import com.ansimue.nht.entities.ReactionEntity;
import com.ansimue.nht.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/toggle")
    public ResponseEntity<ReactionEntity> toggleReaction(@RequestBody ReactionDTO reactionDTO) {
        ReactionEntity result = reactionService.toggleReaction(reactionDTO);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }
}
