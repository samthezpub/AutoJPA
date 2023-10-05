package com.example.autojpa.Repository;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.CargoTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutoRepository extends JpaRepository<AutoEntity, Long> {

    @Query("SELECT a FROM AutoEntity a WHERE a.cargoQuantity>=:weight")
    Optional<List<AutoEntity>> findAutoEntityByWeight(@Param("weight") Double weight);

    @Query("UPDATE AutoEntity a SET a.cargoType = :cargoType WHERE a.id = :id")
    void updateCargoTypeById(@Param("id")Long id, @Param("cargoType")CargoTypeEntity cargoType);


}
