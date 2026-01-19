package com.ansimue.nht.dto;

public class DemandeRechargeDTO {
    private Long userId;
    private Integer montantAr;
    private Integer coinsDemandes;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getMontantAr() {
        return montantAr;
    }

    public void setMontantAr(Integer montantAr) {
        this.montantAr = montantAr;
    }

    public Integer getCoinsDemandes() {
        return coinsDemandes;
    }

    public void setCoinsDemandes(Integer coinsDemandes) {
        this.coinsDemandes = coinsDemandes;
    }
}
