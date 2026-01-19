package com.ansimue.nht.repository;

import com.ansimue.nht.entities.LibraryEntity;
import com.ansimue.nht.entities.StoryEntity;
import com.ansimue.nht.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity, Long> {
    List<LibraryEntity> findByUtilisateur(UserEntity utilisateur);

    Optional<LibraryEntity> findByUtilisateurAndHistoire(UserEntity utilisateur, StoryEntity histoire);
}
