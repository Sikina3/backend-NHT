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
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @GetMapping("/{id}/stats")
    public ResponseEntity<?> getUserStats(@PathVariable Long id) {
        return userService.getUserStats(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity<?> followUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        try {
            userService.followUser(followerId, followedId);
            return ResponseEntity.ok(Map.of("message", "Abonné avec succès", "following", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Impossible de s'abonner", "error", e.getMessage()));
        }
    }

    @PostMapping("/{followerId}/unfollow/{followedId}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        try {
            userService.unfollowUser(followerId, followedId);
            return ResponseEntity.ok(Map.of("message", "Désabonné avec succès", "following", false));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Impossible de se désabonner", "error", e.getMessage()));
        }
    }

    @GetMapping("/{followerId}/is-following/{followedId}")
    public ResponseEntity<?> isFollowing(@PathVariable Long followerId, @PathVariable Long followedId) {
        boolean following = userService.isFollowing(followerId, followedId);
        return ResponseEntity.ok(Map.of("following", following));
    }
}
