package com.example.autojpa.Repository;

import com.example.autojpa.Entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

    @Query("SELECT d FROM DriverEntity d WHERE d.experience>=:experience")
    Optional<DriverEntity> findIdByExperience(@Param("experience") Integer experience);

    @Query("UPDATE DriverEntity SET experience=:expirience WHERE id=:id")
    DriverEntity updateDriverExperienceById(@Param("id") Long id, @Param("experience") Integer experience);

    @Query("UPDATE DriverEntity SET experience=experience+1 WHERE id=:id")
    DriverEntity updateDriverExperienceSetOnePlusById(@Param("id") Long id);
}
