package com.ansimue.nht.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansimue.nht.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNumTel(String numTel);
}
