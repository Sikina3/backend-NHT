package com.ansimue.nht.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansimue.nht.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
}
