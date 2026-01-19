package com.ansimue.nht.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ansimue.nht.model.Category;

import jakarta.persistence.*;

@Entity
@Table(name = "histoires")
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String photoCouverture;

    @Enumerated(EnumType.STRING)
    private Category categories;

    @ManyToOne
    @JoinColumn(name = "ecrivain_id")
    private WritterEntity auteur;

    @OneToMany(mappedBy = "histoire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChapterEntity> chapitres;

    @ManyToMany(mappedBy = "histoires")
    private Set<UserEntity> lecteurs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoCouverture() {
        return photoCouverture;
    }

    public void setPhotoCouverture(String photoCouverture) {
        this.photoCouverture = photoCouverture;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public WritterEntity getAuteur() {
        return auteur;
    }

    public void setAuteur(WritterEntity auteur) {
        this.auteur = auteur;
    }

    public List<ChapterEntity> getChapitres() {
        return chapitres;
    }

    public void setChapitres(List<ChapterEntity> chapitres) {
        this.chapitres = chapitres;
    }

    public Set<UserEntity> getLecteurs() {
        return lecteurs;
    }

    public void setLecteurs(Set<UserEntity> lecteurs) {
        this.lecteurs = lecteurs;
    }
}
