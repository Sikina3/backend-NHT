package com.ansimue.nht.service;

import com.ansimue.nht.dto.CommentaireDTO;
import com.ansimue.nht.entities.CommentaireEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.repository.CommentaireRepository;
import com.ansimue.nht.repository.StoryRepository;
import com.ansimue.nht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    public CommentaireEntity addComment(CommentaireDTO commentaireDTO) {
        UserEntity user = userRepository.findById(commentaireDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        StoryEntity story = storyRepository.findById(commentaireDTO.getStoryId())
                .orElseThrow(() -> new RuntimeException("Story not found"));

        CommentaireEntity commentaire = new CommentaireEntity();
        commentaire.setUtilisateur(user);
        commentaire.setHistoire(story);
        commentaire.setContenu(commentaireDTO.getContenu());
        commentaire.setAuteur(story.getAuteur()); // Automatically link to the story's author

        return commentaireRepository.save(commentaire);
    }

    public List<CommentaireEntity> getCommentsForStory(Long storyId) {
        StoryEntity story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));
        return commentaireRepository.findByHistoire(story);
    }

    public void deleteComment(Long commentId) {
        commentaireRepository.deleteById(commentId);
    }
}
