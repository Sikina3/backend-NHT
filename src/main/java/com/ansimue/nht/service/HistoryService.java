package com.ansimue.nht.service;

import com.ansimue.nht.entities.HistoryEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.repository.HistoryRepository;
import com.ansimue.nht.repository.StoryRepository;
import com.ansimue.nht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    public HistoryEntity addToHistory(Long userId, Long storyId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        StoryEntity story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        HistoryEntity history = historyRepository.findByUtilisateurAndHistoire(user, story)
                .orElse(new HistoryEntity());

        history.setUtilisateur(user);
        history.setHistoire(story);
        history.setDateConsultation(LocalDateTime.now());

        return historyRepository.save(history);
    }

    public List<HistoryEntity> getUserHistory(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return historyRepository.findByUtilisateurOrderByDateConsultationDesc(user);
    }

    public void clearHistory(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<HistoryEntity> history = historyRepository.findByUtilisateurOrderByDateConsultationDesc(user);
        historyRepository.deleteAll(history);
    }
}
