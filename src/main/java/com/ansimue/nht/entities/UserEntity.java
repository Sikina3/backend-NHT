package com.ansimue.nht.entities;

import java.util.HashSet;
import java.util.Set;

import com.ansimue.nht.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(name = "num_tel")
    @JsonProperty("num_tel")
    private String numTel;

    private String ville;

    @Column(name = "mot_de_passe")
    @JsonProperty("mot_de_passe")
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Role role; // admin, ecrivain, lecteur

    @ManyToMany
    @JoinTable(name = "achat", joinColumns = @JoinColumn(name = "lecteur_id"), inverseJoinColumns = @JoinColumn(name = "histoire_id"))
    private Set<StoryEntity> histoires = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "achat_chapitres", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "chapter_id"))
    private Set<ChapterEntity> purchasedChapters = new HashSet<>();

    private Integer soldeCoins = 0;

    public UserEntity() {
    }

    public Integer getSoldeCoins() {
        return soldeCoins;
    }

    public void setSoldeCoins(Integer soldeCoins) {
        this.soldeCoins = soldeCoins;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<StoryEntity> getHistoires() {
        return histoires;
    }

    public void setHistoires(Set<StoryEntity> histoires) {
        this.histoires = histoires;
    }

    public Set<ChapterEntity> getPurchasedChapters() {
        return purchasedChapters;
    }

    public void setPurchasedChapters(Set<ChapterEntity> purchasedChapters) {
        this.purchasedChapters = purchasedChapters;
    }
}
