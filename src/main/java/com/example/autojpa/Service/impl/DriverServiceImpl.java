package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Repository.DriverRepository;
import com.example.autojpa.Service.DriverService;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public DriverEntity saveDriver(DriverEntity driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Optional<DriverEntity> findById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public Optional<DriverEntity> findDriverByExperience(Integer experience) {
        return  driverRepository.findIdByExperience(experience);
    }

    @Override
    @Transactional
    public DriverEntity updateDriver(DriverEntity driver) {
        return driverRepository.save(driver);
    }

    @Override
    @Transactional
    public DriverEntity updateDriverExperienceById(Long id, Integer experience) {
        return driverRepository.updateDriverExperienceById(id, experience);
    }

    @Override
    @Transactional
    public DriverEntity updateDriverExperienceSetOnePlusById(Long id) {
        return driverRepository.updateDriverExperienceSetOnePlusById(id);
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }
}
