package com.ansimue.nht.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String numTel = credentials.get("num_tel");
        String motDePasse = credentials.get("mot_de_passe");

        return userService.login(numTel, motDePasse)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).body("Numéro de téléphone ou mot de passe incorrect"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getProfile(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Upgrade a lecteur to ecrivain.
     * Body: { "biographie": "..." } (optional)
     */
    @PostMapping("/{id}/become-writer")
    public ResponseEntity<?> becomeWriter(
            @PathVariable Long id,
            @RequestBody(required = false) Map<String, String> body) {
        String bio = body != null ? body.getOrDefault("biographie", "") : "";

        return userService.becomeWriter(id, bio)
                .map(user -> ResponseEntity.ok((Object) user))
                .orElseGet(() -> ResponseEntity.status(404).body("Utilisateur avec l'ID " + id
                        + " non trouvé. Vérifiez que l'ID est correct dans la base de données."));
    }
}
