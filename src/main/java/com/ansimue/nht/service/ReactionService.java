package com.ansimue.nht.service;

import com.ansimue.nht.dto.ReactionDTO;
import com.ansimue.nht.entities.ReactionEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.repository.ReactionRepository;
import com.ansimue.nht.repository.StoryRepository;
import com.ansimue.nht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    public ReactionEntity toggleReaction(ReactionDTO reactionDTO) {
        UserEntity user = userRepository.findById(reactionDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        StoryEntity story = storyRepository.findById(reactionDTO.getStoryId())
                .orElseThrow(() -> new RuntimeException("Story not found"));

        Optional<ReactionEntity> existing = reactionRepository.findByUtilisateurAndHistoire(user, story);

        if (existing.isPresent()) {
            if (existing.get().getType() == reactionDTO.getType()) {
                reactionRepository.delete(existing.get());
                return null;
            } else {
                existing.get().setType(reactionDTO.getType());
                return reactionRepository.save(existing.get());
            }
        }

        ReactionEntity reaction = new ReactionEntity();
        reaction.setUtilisateur(user);
        reaction.setHistoire(story);
        reaction.setType(reactionDTO.getType());
        reaction.setAuteur(story.getAuteur());

        return reactionRepository.save(reaction);
    }
}
