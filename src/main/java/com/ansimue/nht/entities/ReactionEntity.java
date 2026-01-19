package com.ansimue.nht.entities;

import com.ansimue.nht.model.ReactionType;

import jakarta.persistence.*;

@Entity
@Table(name = "reactions")
public class ReactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserEntity utilisateur;

    @ManyToOne
    private StoryEntity histoire;

    @ManyToOne
    private WritterEntity auteur;

    @Enumerated(EnumType.STRING)
    private ReactionType type;

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

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }
}
