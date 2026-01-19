package com.ansimue.nht.controller;

import com.ansimue.nht.dto.DemandeRechargeDTO;
import com.ansimue.nht.entities.DemandeRecharge;
import com.ansimue.nht.service.DemandeRechargeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/recharges")
public class DemandeRechargeController {

    @Autowired
    private DemandeRechargeService demandeRechargeService;

    @PostMapping("/request")
    public ResponseEntity<DemandeRecharge> createDemande(
            @RequestParam("data") String dataJson,
            @RequestParam(value = "capture", required = false) MultipartFile capture) throws Exception {

        DemandeRechargeDTO dto = new ObjectMapper().readValue(dataJson, DemandeRechargeDTO.class);
        return ResponseEntity.ok(demandeRechargeService.createDemande(dto, capture));
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<DemandeRecharge> approveDemande(@PathVariable Long id) {
        return ResponseEntity.ok(demandeRechargeService.approveDemande(id));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<DemandeRecharge> rejectDemande(@PathVariable Long id) {
        return ResponseEntity.ok(demandeRechargeService.rejectDemande(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DemandeRecharge>> getAllDemandes() {
        return ResponseEntity.ok(demandeRechargeService.getAllDemandes());
    }
}
