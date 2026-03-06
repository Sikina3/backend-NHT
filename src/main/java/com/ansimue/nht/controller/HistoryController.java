package com.ansimue.nht.controller;

import com.ansimue.nht.entities.HistoryEntity;
import com.ansimue.nht.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/add")
    public ResponseEntity<HistoryEntity> addToHistory(@RequestParam Long userId, @RequestParam Long storyId) {
        return ResponseEntity.ok(historyService.addToHistory(userId, storyId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HistoryEntity>> getUserHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(historyService.getUserHistory(userId));
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearHistory(@PathVariable Long userId) {
        historyService.clearHistory(userId);
        return ResponseEntity.ok().build();
    }
}
