package com.example.autojpa.Repository;

import com.example.autojpa.Entity.AutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends JpaRepository<AutoEntity, Long> {
}
