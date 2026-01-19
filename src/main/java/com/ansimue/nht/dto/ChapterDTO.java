package com.ansimue.nht.dto;

public class ChapterDTO {
    private String sousTitre;
    private String contenu;
    private Integer numeroChapitre;
    private Long histoireId;
    private Integer coutCoins;

    // Getters and Setters
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

    public Long getHistoireId() {
        return histoireId;
    }

    public void setHistoireId(Long histoireId) {
        this.histoireId = histoireId;
    }

    public Integer getCoutCoins() {
        return coutCoins;
    }

    public void setCoutCoins(Integer coutCoins) {
        this.coutCoins = coutCoins;
    }
}
