package com.example.autojpa.Repository;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

    @Query("SELECT d FROM DriverEntity d WHERE d.experience>=:experience AND d.isFree=true")
    Optional<DriverEntity> findIdByExperienceAndFree(@Param("experience") Integer experience);

    @Query("SELECT d FROM DriverEntity d WHERE d.request.id=:request_id")
    Optional<DriverEntity> findDriverEntityByRequestId(@Param("request_id") Long request_id);

    @Query("UPDATE DriverEntity d SET d.experience=:experience WHERE d.id=:id")
    DriverEntity updateDriverExperienceById(@Param("id") Long id, @Param("experience") Integer experience);

    @Query("UPDATE DriverEntity d SET d.experience=d.experience+1 WHERE d.id=:id")
    DriverEntity updateDriverExperienceSetOnePlusById(@Param("id") Long id);

    @Query("UPDATE DriverEntity d SET d.auto = :auto WHERE d.id=:id")
    DriverEntity updateDriverAutoById(@Param("auto")AutoEntity auto, @Param("id") Long id);

    @Query("UPDATE DriverEntity d SET d.request = :request WHERE d.id=:id")
    DriverEntity updateDriverRequestById(@Param("request") RequestEntity request, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE DriverEntity d SET d.request=null, d.auto=null WHERE d.id=:id")
    void updateDriverDoneDestination(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE RequestEntity r SET r.isDone=true WHERE r.id=(SELECT d.request.id FROM DriverEntity d WHERE d.id=:id)")
    void updateRequestIsDone(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE DriverEntity d SET d.money= d.money + :money WHERE d.id=:id")
    void updateDriverMoneyById(@Param("id")Long id, @Param("money") double money);
}
