package com.ansimue.nht.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "historique")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity utilisateur;

    @ManyToOne
    @JoinColumn(name = "histoire_id")
    private StoryEntity histoire;

    private LocalDateTime dateConsultation = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UserEntity utilisateur) {
        this.utilisateur = utilisateur;
    }

    public StoryEntity getHistoire() {
        return histoire;
    }

    public void setHistoire(StoryEntity histoire) {
        this.histoire = histoire;
    }

    public LocalDateTime getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(LocalDateTime dateConsultation) {
        this.dateConsultation = dateConsultation;
    }
}
