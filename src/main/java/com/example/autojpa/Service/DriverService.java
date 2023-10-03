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
    Optional<DriverEntity> findDriverByExperience(@Param("experience")Integer experience);
    DriverEntity updateDriver(DriverEntity driver);
    DriverEntity updateDriverExperienceById(Long id, Integer experience);
    DriverEntity updateDriverExperienceSetOnePlusById(@Param("id") Long id);
    DriverEntity updateDriverAutoById(AutoEntity auto, Long id);
    DriverEntity updateDriverRequestById(@Param("auto") RequestEntity request, @Param("id") Long id);
    void updateDriverDoneDestination(@Param("id")Long id);
    void deleteById(Long id);
}
