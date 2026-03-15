package com.ansimue.nht.service;

import com.ansimue.nht.dto.StoryDTO;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.repository.StoryRepository;
import com.ansimue.nht.repository.WritterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private WritterRepository writterRepository;

    public StoryEntity createStory(StoryDTO storyDTO, MultipartFile image) {
        StoryEntity story = new StoryEntity();
        story.setTitre(storyDTO.getTitre());
        story.setDescription(storyDTO.getDescription());
        story.setCategories(storyDTO.getCategories());
        story.setPrixCoins(storyDTO.getPrixCoins() != null ? storyDTO.getPrixCoins() : 0);

        if (storyDTO.getAuteurId() != null) {
            story.setAuteur(writterRepository.findById(storyDTO.getAuteurId()).orElse(null));
        }

        if (image != null && !image.isEmpty()) {
            String fileName = fileStorageService.storeFile(image);
            story.setPhotoCouverture(fileName);
        }
        return storyRepository.save(story);
    }

    public List<StoryEntity> getAllStories() {
        return storyRepository.findAll();
    }

    public Optional<StoryEntity> getStoryById(Long id) {
        return storyRepository.findById(id);
    }

    public StoryEntity updateStory(Long id, StoryDTO storyDTO, MultipartFile image) {
        StoryEntity story = storyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found with id: " + id));

        story.setTitre(storyDTO.getTitre());
        story.setDescription(storyDTO.getDescription());
        story.setCategories(storyDTO.getCategories());
        story.setPrixCoins(storyDTO.getPrixCoins());

        if (storyDTO.getAuteurId() != null) {
            story.setAuteur(writterRepository.findById(storyDTO.getAuteurId()).orElse(null));
        }

        if (image != null && !image.isEmpty()) {
            String fileName = fileStorageService.storeFile(image);
            story.setPhotoCouverture(fileName);
        }

        return storyRepository.save(story);
    }

    public void deleteStory(Long id) {
        storyRepository.deleteById(id);
    }

    public List<StoryEntity> searchStories(String query) {
        return storyRepository.findByTitreContainingIgnoreCase(query);
    }

    public List<StoryEntity> getStoriesByCategory(com.ansimue.nht.model.Category category) {
        return storyRepository.findByCategories(category);
    }

    public void incrementVues(Long id) {
        storyRepository.findById(id).ifPresent(story -> {
            story.setVues((story.getVues() == null ? 0 : story.getVues()) + 1);
            storyRepository.save(story);
        });
    }

    public void incrementLikes(Long id) {
        storyRepository.findById(id).ifPresent(story -> {
            story.setLikes((story.getLikes() == null ? 0 : story.getLikes()) + 1);
            storyRepository.save(story);
        });
    }
}
