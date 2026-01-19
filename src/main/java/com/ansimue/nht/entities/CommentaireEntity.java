package com.ansimue.nht.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "commentaires")
public class CommentaireEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity utilisateur;

    @ManyToOne
    @JoinColumn(name = "histoire_id")
    private StoryEntity histoire;

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private WritterEntity auteur;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    private LocalDateTime dateCommentaire = LocalDateTime.now();

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

    public WritterEntity getAuteur() {
        return auteur;
    }

    public void setAuteur(WritterEntity auteur) {
        this.auteur = auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(LocalDateTime dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }
}