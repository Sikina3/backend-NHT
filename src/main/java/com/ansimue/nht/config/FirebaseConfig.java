package com.ansimue.nht.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.credentials.path:firebase-service-account.json}")
    private String credentialsPath;

    @Value("${firebase.storage.bucket:ndao-hamaky-tantara-cc5a2.firebasestorage.app}")
    private String storageBucket;

    @PostConstruct
    public void initializeFirebase() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                InputStream serviceAccount = new ClassPathResource(credentialsPath).getInputStream();
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setStorageBucket(storageBucket)
                        .build();
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase Admin SDK initialisé avec succès.");
            }
        } catch (IOException e) {
            System.err.println("⚠️ Impossible de charger le fichier de credentials Firebase: " + credentialsPath);
            System.err.println("   Les uploads d'images ne fonctionneront pas.");
            // On ne plante pas l'app si Firebase n'est pas configuré
        }
    }
}
