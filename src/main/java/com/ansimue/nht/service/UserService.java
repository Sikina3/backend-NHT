package com.ansimue.nht.service;

import org.springframework.stereotype.Service;
import com.ansimue.nht.entities.UserEntity;
import com.ansimue.nht.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity createUser(UserEntity user) {
        if (user.getRole() == com.ansimue.nht.model.Role.ecrivain) {
            com.ansimue.nht.entities.WritterEntity writter = new com.ansimue.nht.entities.WritterEntity();
            writter.setNom(user.getNom());
            writter.setNumTel(user.getNumTel());
            writter.setVille(user.getVille());
            writter.setMotDePasse(user.getMotDePasse());
            writter.setRole(user.getRole());
            return userRepository.save(writter);
        }
        return userRepository.save(user);
    }
}
