package com.ansimue.nht.controller;

import com.ansimue.nht.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "*")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/story")
    public ResponseEntity<?> purchaseStory(@RequestParam Long userId, @RequestParam Long storyId) {
        try {
            purchaseService.purchaseStory(userId, storyId);
            return ResponseEntity.ok("Story purchased successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/chapter")
    public ResponseEntity<?> purchaseChapter(@RequestParam Long userId, @RequestParam Long chapterId) {
        try {
            purchaseService.purchaseChapter(userId, chapterId);
            return ResponseEntity.ok("Chapter purchased successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-access")
    public ResponseEntity<Boolean> checkAccess(@RequestParam Long userId, @RequestParam Long chapterId) {
        return ResponseEntity.ok(purchaseService.hasAccessToChapter(userId, chapterId));
    }
}
