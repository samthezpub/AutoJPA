package com.example.autojpa.Service;

import com.example.autojpa.Entity.DriverEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DriverService {
    DriverEntity saveDriver(DriverEntity driver);
    Optional<DriverEntity> findById(Long id);
    Optional<DriverEntity> findDriverByExperience(@Param("experience")Integer experience);
    DriverEntity updateDriver(DriverEntity driver);
    DriverEntity updateDriverExperienceById(Long id, Integer experience);
    DriverEntity updateDriverExperienceSetOnePlusById(@Param("id") Long id);
    void deleteById(Long id);
}
