package com.ansimue.nht.service;

import com.ansimue.nht.dto.ChapterDTO;
import com.ansimue.nht.entities.ChapterEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.repository.ChapterRepository;
import com.ansimue.nht.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private StoryRepository storyRepository;

    public ChapterEntity addChapter(ChapterDTO chapterDTO) {
        StoryEntity story = storyRepository.findById(chapterDTO.getHistoireId())
                .orElseThrow(() -> new RuntimeException("Story not found"));

        ChapterEntity chapter = new ChapterEntity();
        chapter.setSousTitre(chapterDTO.getSousTitre());
        chapter.setContenu(chapterDTO.getContenu());
        chapter.setNumeroChapitre(chapterDTO.getNumeroChapitre());
        chapter.setHistoire(story);
        chapter.setCoutCoins(chapterDTO.getCoutCoins() != null ? chapterDTO.getCoutCoins() : 0);

        return chapterRepository.save(chapter);
    }

    public List<ChapterEntity> getChaptersByStory(Long storyId) {
        StoryEntity story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story not found"));
        return chapterRepository.findByHistoireOrderByNumeroChapitreAsc(story);
    }

    public ChapterEntity updateChapter(Long id, ChapterDTO chapterDTO) {
        ChapterEntity chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chapter not found"));

        chapter.setSousTitre(chapterDTO.getSousTitre());
        chapter.setContenu(chapterDTO.getContenu());
        chapter.setNumeroChapitre(chapterDTO.getNumeroChapitre());
        chapter.setCoutCoins(chapterDTO.getCoutCoins());

        return chapterRepository.save(chapter);
    }

    public void deleteChapter(Long id) {
        chapterRepository.deleteById(id);
    }
}
