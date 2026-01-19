package com.ansimue.nht.entities;

import java.time.LocalDateTime;

import com.ansimue.nht.model.StatutRecharge;

import jakarta.persistence.*;

@Entity
@Table(name = "recharges_attente")
public class DemandeRecharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity utilisateur;

    private Integer montantAr;
    private Integer coinsDemandes;

    private String urlCaptureEcran;

    @Enumerated(EnumType.STRING)
    private StatutRecharge statut = StatutRecharge.EN_ATTENTE;

    private LocalDateTime dateDemande = LocalDateTime.now();

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

    public String getUrlCaptureEcran() {
        return urlCaptureEcran;
    }

    public void setUrlCaptureEcran(String urlCaptureEcran) {
        this.urlCaptureEcran = urlCaptureEcran;
    }

    public StatutRecharge getStatut() {
        return statut;
    }

    public void setStatut(StatutRecharge statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }
}
