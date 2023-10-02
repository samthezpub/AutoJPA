package com.example.autojpa.Repository;

import com.example.autojpa.Entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {
}
