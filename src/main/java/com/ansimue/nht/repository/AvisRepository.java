package com.ansimue.nht.repository;

import com.ansimue.nht.entities.AvisEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.WritterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<AvisEntity, Long> {
    List<AvisEntity> findByHistoire(StoryEntity histoire);

    List<AvisEntity> findByAuteur(WritterEntity auteur);
}
