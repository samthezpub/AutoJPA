package com.example.autojpa.Repository;

import com.example.autojpa.Entity.RepairRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRequestRepository extends JpaRepository<RepairRequestEntity, Long> {
}
