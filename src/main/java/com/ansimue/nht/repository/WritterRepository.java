package com.ansimue.nht.repository;

import com.ansimue.nht.entities.WritterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WritterRepository extends JpaRepository<WritterEntity, Long> {
}
