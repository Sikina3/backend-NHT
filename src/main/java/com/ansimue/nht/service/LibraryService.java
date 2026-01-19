package com.ansimue.nht.service;

import com.ansimue.nht.dto.LibraryDTO;
import com.ansimue.nht.entities.LibraryEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.repository.LibraryRepository;
import com.ansimue.nht.repository.StoryRepository;
import com.ansimue.nht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    public LibraryEntity addToLibrary(LibraryDTO libraryDTO) {
        UserEntity user = userRepository.findById(libraryDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        StoryEntity story = storyRepository.findById(libraryDTO.getStoryId())
                .orElseThrow(() -> new RuntimeException("Story not found"));

        Optional<LibraryEntity> existing = libraryRepository.findByUtilisateurAndHistoire(user, story);
        if (existing.isPresent()) {
            return existing.get();
        }

        LibraryEntity library = new LibraryEntity();
        library.setUtilisateur(user);
        library.setHistoire(story);
        library.setDernierChapitreLu(1);
        return libraryRepository.save(library);
    }

    public List<LibraryEntity> getUserLibrary(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return libraryRepository.findByUtilisateur(user);
    }

    public LibraryEntity updateProgress(Long userId, Long storyId, Integer chapterNumber) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        StoryEntity story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        LibraryEntity library = libraryRepository.findByUtilisateurAndHistoire(user, story)
                .orElseGet(() -> {
                    LibraryEntity newLib = new LibraryEntity();
                    newLib.setUtilisateur(user);
                    newLib.setHistoire(story);
                    return newLib;
                });

        library.setDernierChapitreLu(chapterNumber);
        return libraryRepository.save(library);
    }

    public void removeFromLibrary(Long userId, Long storyId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        StoryEntity story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        libraryRepository.findByUtilisateurAndHistoire(user, story)
                .ifPresent(libraryRepository::delete);
    }
}
