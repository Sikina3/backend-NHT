package com.ansimue.nht.repository;

import com.ansimue.nht.entities.HistoryEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    List<HistoryEntity> findByUtilisateurOrderByDateConsultationDesc(UserEntity utilisateur);

    Optional<HistoryEntity> findByUtilisateurAndHistoire(UserEntity utilisateur, StoryEntity histoire);
}
