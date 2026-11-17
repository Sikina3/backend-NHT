package com.ansimue.nht.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/** Lightweight endpoint for confirming that the latest backend deployment is live. */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping("/deploy-check")
    public ResponseEntity<Map<String, String>> deployCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "service", "nht-backend",
                "deploymentMarker", "nht-deploy-check-2026-11-17"
        ));
    }
}
