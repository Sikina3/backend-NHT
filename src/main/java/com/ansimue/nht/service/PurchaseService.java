package com.ansimue.nht.service;

import com.ansimue.nht.dto.TransactionDTO;
import com.ansimue.nht.entities.ChapterEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.model.TransactionType;
import com.ansimue.nht.repository.ChapterRepository;
import com.ansimue.nht.repository.StoryRepository;
import com.ansimue.nht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private TransactionService transactionService;

    @Transactional
    public void purchaseStory(Long userId, Long storyId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        StoryEntity story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        if (user.getHistoires().contains(story)) {
            throw new RuntimeException("Story already purchased");
        }

        if (user.getSoldeCoins() < story.getPrixCoins()) {
            throw new RuntimeException("Insufficient balance");
        }

        // Create transaction
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setUserId(userId);
        transactionDTO.setMontantCoins(-story.getPrixCoins());
        transactionDTO.setType(TransactionType.ACHAT_HISTOIRE);
        transactionService.createTransaction(transactionDTO);

        // Add to purchased stories
        user.getHistoires().add(story);
        userRepository.save(user);
    }

    @Transactional
    public void purchaseChapter(Long userId, Long chapterId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ChapterEntity chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        // Check if story or chapter is already purchased
        if (user.getHistoires().contains(chapter.getHistoire()) || user.getPurchasedChapters().contains(chapter)) {
            return;
        }

        if (user.getSoldeCoins() < chapter.getCoutCoins()) {
            throw new RuntimeException("Insufficient balance");
        }

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setUserId(userId);
        transactionDTO.setMontantCoins(-chapter.getCoutCoins());
        transactionDTO.setType(TransactionType.ACHAT_HISTOIRE);
        transactionService.createTransaction(transactionDTO);

        user.getPurchasedChapters().add(chapter);
        userRepository.save(user);
    }

    public boolean hasAccessToChapter(Long userId, Long chapterId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ChapterEntity chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        return chapter.getCoutCoins() == 0
                || user.getHistoires().contains(chapter.getHistoire())
                || user.getPurchasedChapters().contains(chapter);
    }
}
