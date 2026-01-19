package com.ansimue.nht.entities;

import java.time.LocalDateTime;

import com.ansimue.nht.model.TransactionType;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity utilisateur;

    // + ou - (ex: -5 achat, +20 recharge)
    private Integer montantCoins;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime dateTransaction = LocalDateTime.now();

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

    public Integer getMontantCoins() {
        return montantCoins;
    }

    public void setMontantCoins(Integer montantCoins) {
        this.montantCoins = montantCoins;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
}
