package com.ansimue.nht.repository;

import com.ansimue.nht.entities.DemandeRecharge;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.model.StatutRecharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRechargeRepository extends JpaRepository<DemandeRecharge, Long> {
    List<DemandeRecharge> findByUtilisateur(UserEntity utilisateur);

    List<DemandeRecharge> findByStatut(StatutRecharge statut);
}
