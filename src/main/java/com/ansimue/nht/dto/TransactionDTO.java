package com.ansimue.nht.dto;

import com.ansimue.nht.model.TransactionType;

public class TransactionDTO {
    private Long userId;
    private Integer montantCoins;
    private TransactionType type;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
