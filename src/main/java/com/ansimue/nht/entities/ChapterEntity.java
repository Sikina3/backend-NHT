package com.ansimue.nht.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "chapitres")
public class ChapterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sousTitre;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    private Integer numeroChapitre = 1;

    @ManyToOne
    @JoinColumn(name = "histoire_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private StoryEntity histoire;

    private Integer coutCoins = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Integer getNumeroChapitre() {
        return numeroChapitre;
    }

    public void setNumeroChapitre(Integer numeroChapitre) {
        this.numeroChapitre = numeroChapitre;
    }

    public StoryEntity getHistoire() {
        return histoire;
    }

    public void setHistoire(StoryEntity histoire) {
        this.histoire = histoire;
    }

    public Integer getCoutCoins() {
        return coutCoins;
    }

    public void setCoutCoins(Integer coutCoins) {
        this.coutCoins = coutCoins;
    }
}
