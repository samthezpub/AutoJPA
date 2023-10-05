package com.example.autojpa.Service;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Entity.RequestEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DriverService {
    DriverEntity saveDriver(DriverEntity driver);
    List<DriverEntity> findAll();
    Optional<DriverEntity> findById(Long id);
    Optional<DriverEntity> findDriverByExperienceAndFree(@Param("experience")Integer experience);
    Optional<DriverEntity> findDriverEntityByRequestId(@Param("request_id") Long request_id);

    DriverEntity updateDriver(DriverEntity driver);
    DriverEntity updateDriverExperienceById(Long id, Integer experience);
    DriverEntity updateDriverExperienceSetOnePlusById(@Param("id") Long id);
    DriverEntity updateDriverAutoById(AutoEntity auto, Long id);
    DriverEntity updateDriverRequestById(@Param("auto") RequestEntity request, @Param("id") Long id);
    void updateDriverDoneDestination(@Param("id")Long id);
    void updateDriverMoneyById(@Param("id")Long id, @Param("money") double money);
    void deleteById(Long id);
    void reportArrival(Long driverId);
}
