package com.ansimue.nht.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ecrivains")
public class WritterEntity extends UserEntity {
    @Column(columnDefinition = "TEXT")
    private String biographie;

    @OneToMany(mappedBy = "auteur")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<StoryEntity> histoires;

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String bio) {
        this.biographie = bio;
    }

    public List<StoryEntity> getHistory() {
        return histoires;
    }

    public void setHistory(List<StoryEntity> histoires) {
        this.histoires = histoires;
    }
}
