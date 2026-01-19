package com.ansimue.nht.repository;

import com.ansimue.nht.entities.ReactionEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<ReactionEntity, Long> {
    List<ReactionEntity> findByHistoire(StoryEntity histoire);

    Optional<ReactionEntity> findByUtilisateurAndHistoire(UserEntity utilisateur, StoryEntity histoire);
}
