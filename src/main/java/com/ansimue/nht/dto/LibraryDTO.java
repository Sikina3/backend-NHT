package com.ansimue.nht.dto;

import java.time.LocalDateTime;

public class LibraryDTO {
    private Long userId;
    private Long storyId;
    private Integer dernierChapitreLu;
    private LocalDateTime dateAjout;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public Integer getDernierChapitreLu() {
        return dernierChapitreLu;
    }

    public void setDernierChapitreLu(Integer dernierChapitreLu) {
        this.dernierChapitreLu = dernierChapitreLu;
    }

    public LocalDateTime getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }
}
