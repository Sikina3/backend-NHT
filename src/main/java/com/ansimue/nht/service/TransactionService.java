package com.ansimue.nht.service;

import com.ansimue.nht.dto.TransactionDTO;
import com.ansimue.nht.entities.TransactionEntity;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.model.TransactionType;
import com.ansimue.nht.repository.TransactionRepository;
import com.ansimue.nht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public TransactionEntity createTransaction(TransactionDTO transactionDTO) {
        UserEntity user = userRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setUtilisateur(user);
        transaction.setMontantCoins(transactionDTO.getMontantCoins());
        transaction.setType(transactionDTO.getType());

        // Update user balance
        if (transactionDTO.getType() == TransactionType.RECHARGE_COINS
                || transactionDTO.getType() == TransactionType.BONUS) {
            user.setSoldeCoins(user.getSoldeCoins() + transactionDTO.getMontantCoins());
        } else {
            if (user.getSoldeCoins() < Math.abs(transactionDTO.getMontantCoins())) {
                throw new RuntimeException("Insufficient balance");
            }
            user.setSoldeCoins(user.getSoldeCoins() - Math.abs(transactionDTO.getMontantCoins()));
        }

        userRepository.save(user);
        return transactionRepository.save(transaction);
    }

    public List<TransactionEntity> getUserTransactions(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUtilisateurOrderByDateTransactionDesc(user);
    }
}
