package com.ansimue.nht.service;

import com.ansimue.nht.dto.AvisDTO;
import com.ansimue.nht.entities.AvisEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.entities.WritterEntity;
import com.ansimue.nht.repository.AvisRepository;
import com.ansimue.nht.repository.StoryRepository;
import com.ansimue.nht.repository.UserRepository;
import com.ansimue.nht.repository.WritterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisService {

    @Autowired
    private AvisRepository avisRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private WritterRepository writterRepository;

    public AvisEntity addAvis(AvisDTO avisDTO) {
        UserEntity user = userRepository.findById(avisDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        AvisEntity avis = new AvisEntity();
        avis.setUtilisateur(user);
        avis.setNote(avisDTO.getNote());
        avis.setContenu(avisDTO.getContenu());

        if (avisDTO.getStoryId() != null) {
            StoryEntity story = storyRepository.findById(avisDTO.getStoryId())
                    .orElseThrow(() -> new RuntimeException("Story not found"));
            avis.setHistoire(story);
        }

        if (avisDTO.getAuteurId() != null) {
            WritterEntity auteur = writterRepository.findById(avisDTO.getAuteurId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            avis.setAuteur(auteur);
        }

        return avisRepository.save(avis);
    }

    public List<AvisEntity> getAvisByStory(Long storyId) {
        StoryEntity story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));
        return avisRepository.findByHistoire(story);
    }
}
