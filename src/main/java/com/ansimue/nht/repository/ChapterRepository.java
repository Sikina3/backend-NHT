package com.ansimue.nht.repository;

import com.ansimue.nht.entities.ChapterEntity;
import com.ansimue.nht.entities.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {
    List<ChapterEntity> findByHistoireOrderByNumeroChapitreAsc(StoryEntity histoire);
}
