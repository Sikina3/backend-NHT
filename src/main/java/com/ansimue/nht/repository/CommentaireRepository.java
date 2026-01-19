package com.ansimue.nht.repository;

import com.ansimue.nht.entities.CommentaireEntity;
import com.ansimue.nht.entities.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<CommentaireEntity, Long> {
    List<CommentaireEntity> findByHistoire(StoryEntity histoire);
}
