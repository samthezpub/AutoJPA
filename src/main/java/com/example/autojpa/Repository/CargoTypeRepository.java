package com.example.autojpa.Repository;

import com.example.autojpa.Entity.CargoTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoTypeRepository extends JpaRepository<CargoTypeEntity, Long> {
}
