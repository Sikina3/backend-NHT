package com.ansimue.nht.controller;

import com.ansimue.nht.dto.AvisDTO;
import com.ansimue.nht.entities.AvisEntity;
import com.ansimue.nht.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
@CrossOrigin(origins = "*")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @PostMapping("/add")
    public ResponseEntity<AvisEntity> addAvis(@RequestBody AvisDTO avisDTO) {
        return ResponseEntity.ok(avisService.addAvis(avisDTO));
    }

    @GetMapping("/story/{storyId}")
    public ResponseEntity<List<AvisEntity>> getAvisByStory(@PathVariable Long storyId) {
        return ResponseEntity.ok(avisService.getAvisByStory(storyId));
    }
}
