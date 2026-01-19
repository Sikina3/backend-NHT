package com.ansimue.nht.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "avis")
public class AvisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserEntity utilisateur;

    @ManyToOne
    private StoryEntity histoire; // nullable

    @ManyToOne
    private WritterEntity auteur; // nullable

    private Integer note; // 1 à 5

    @Column(columnDefinition = "TEXT")
    private String contenu;

    private LocalDateTime dateAvis = LocalDateTime.now();

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

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateAvis() {
        return dateAvis;
    }

    public void setDateAvis(LocalDateTime dateAvis) {
        this.dateAvis = dateAvis;
    }
}