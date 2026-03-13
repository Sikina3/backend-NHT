package com.ansimue.nht.repository;

import com.ansimue.nht.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ansimue.nht.entities.StoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, Long> {
    List<StoryEntity> findByTitreContainingIgnoreCase(String titre);

    List<StoryEntity> findByCategories(Category category);

    int countByAuteur(com.ansimue.nht.entities.WritterEntity auteur);
}
