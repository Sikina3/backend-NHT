package com.ansimue.nht.service;

import com.ansimue.nht.dto.DemandeRechargeDTO;
import com.ansimue.nht.dto.TransactionDTO;
import com.ansimue.nht.entities.DemandeRecharge;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.model.StatutRecharge;
import com.ansimue.nht.model.TransactionType;
import com.ansimue.nht.repository.DemandeRechargeRepository;
import com.ansimue.nht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DemandeRechargeService {

    @Autowired
    private DemandeRechargeRepository demandeRechargeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private TransactionService transactionService;

    public DemandeRecharge createDemande(DemandeRechargeDTO dto, MultipartFile capture) {
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        DemandeRecharge demande = new DemandeRecharge();
        demande.setUtilisateur(user);
        demande.setMontantAr(dto.getMontantAr());
        demande.setCoinsDemandes(dto.getCoinsDemandes());
        demande.setStatut(StatutRecharge.EN_ATTENTE);

        if (capture != null && !capture.isEmpty()) {
            String fileName = fileStorageService.storeFile(capture);
            demande.setUrlCaptureEcran(fileName);
        }

        return demandeRechargeRepository.save(demande);
    }

    @Transactional
    public DemandeRecharge approveDemande(Long demandeId) {
        DemandeRecharge demande = demandeRechargeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));

        if (demande.getStatut() != StatutRecharge.EN_ATTENTE) {
            throw new RuntimeException("Demande already processed");
        }

        demande.setStatut(StatutRecharge.APPROUVEE);

        // Create transaction to add coins
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setUserId(demande.getUtilisateur().getId());
        transactionDTO.setMontantCoins(demande.getCoinsDemandes());
        transactionDTO.setType(TransactionType.RECHARGE);
        transactionService.createTransaction(transactionDTO);

        return demandeRechargeRepository.save(demande);
    }

    public DemandeRecharge rejectDemande(Long demandeId) {
        DemandeRecharge demande = demandeRechargeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));
        demande.setStatut(StatutRecharge.REJETEE);
        return demandeRechargeRepository.save(demande);
    }

    public List<DemandeRecharge> getAllDemandes() {
        return demandeRechargeRepository.findAll();
    }
}
