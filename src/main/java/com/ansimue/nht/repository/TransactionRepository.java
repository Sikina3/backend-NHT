package com.ansimue.nht.repository;

import com.ansimue.nht.entities.TransactionEntity;
import com.ansimue.nht.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByUtilisateurOrderByDateTransactionDesc(UserEntity utilisateur);
}
