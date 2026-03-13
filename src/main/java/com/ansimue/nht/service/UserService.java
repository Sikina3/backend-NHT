package com.ansimue.nht.service;

import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.entities.WritterEntity;
import com.ansimue.nht.model.Role;
import com.ansimue.nht.repository.UserRepository;
import com.ansimue.nht.repository.WritterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import com.ansimue.nht.repository.StoryRepository;
import com.ansimue.nht.repository.HistoryRepository;
import com.ansimue.nht.dto.UserStatsDTO;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WritterRepository writtersRepository;
    private final StoryRepository storyRepository;
    private final HistoryRepository historyRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository, WritterRepository writtersRepository,
            StoryRepository storyRepository, HistoryRepository historyRepository) {
        this.userRepository = userRepository;
        this.writtersRepository = writtersRepository;
        this.storyRepository = storyRepository;
        this.historyRepository = historyRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity createUser(UserEntity user) {
        if (user.getRole() == com.ansimue.nht.model.Role.ecrivain) {
            com.ansimue.nht.entities.WritterEntity writter = new com.ansimue.nht.entities.WritterEntity();
            writter.setNom(user.getNom());
            writter.setNumTel(user.getNumTel());
            writter.setVille(user.getVille());
            writter.setMotDePasse(user.getMotDePasse());
            writter.setRole(user.getRole());
            writter.setSoldeCoins(0);
            return userRepository.save(writter);
        }
        user.setSoldeCoins(0);
        return userRepository.save(user);
    }

    public Optional<UserEntity> login(String numTel, String motDePasse) {
        return userRepository.findByNumTel(numTel)
                .filter(user -> user.getMotDePasse().equals(motDePasse));
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Upgrades a lecteur to ecrivain safely.
     * Uses native queries to bypass JPA inheritance swap restrictions.
     */
    @Transactional
    public Optional<UserEntity> becomeWriter(Long userId, String biographie) {
        Optional<UserEntity> optUser = userRepository.findById(userId);
        if (optUser.isEmpty())
            return Optional.empty();

        UserEntity user = optUser.get();
        if (user.getRole() == Role.ecrivain)
            return Optional.of(user);

        // 1. Update the role in 'users' table
        entityManager.createNativeQuery("UPDATE users SET role = 'ecrivain' WHERE id = :id")
                .setParameter("id", userId)
                .executeUpdate();

        // 2. Insert into 'ecrivains' table (JOINED inheritance table for extra data)
        // We check if it already exists to be safe
        entityManager.createNativeQuery(
                "INSERT INTO ecrivains (id, biographie) VALUES (:id, :bio) " +
                        "ON CONFLICT (id) DO UPDATE SET biographie = EXCLUDED.biographie")
                .setParameter("id", userId)
                .setParameter("bio", biographie != null ? biographie : "")
                .executeUpdate();

        // Refresh entity to get updated state
        entityManager.flush();
        entityManager.clear();
        return userRepository.findById(userId);
    }

    public Optional<UserStatsDTO> getUserStats(Long userId) {
        return userRepository.findById(userId).map(user -> {
            int storiesCount = 0;
            if (user.getRole() == Role.ecrivain) {
                // To fetch safely without mapping issues, find WritterEntity or cast
                Optional<WritterEntity> writter = writtersRepository.findById(userId);
                if (writter.isPresent()) {
                    storiesCount = storyRepository.countByAuteur(writter.get());
                }
            }
            int totalReads = historyRepository.countByUtilisateur(user);
            int followers = user.getFollowers() != null ? user.getFollowers().size() : 0;
            int following = user.getFollowing() != null ? user.getFollowing().size() : 0;

            return new UserStatsDTO(storiesCount, totalReads, followers, following);
        });
    }
}
