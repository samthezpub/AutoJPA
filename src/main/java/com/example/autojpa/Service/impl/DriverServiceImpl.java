package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Entity.RequestEntity;
import com.example.autojpa.Repository.DriverRepository;
import com.example.autojpa.Service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
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
    public List<DriverEntity> findAll() {
        return driverRepository.findAll();
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
    public DriverEntity updateDriverAutoById(AutoEntity auto, Long id) {
        return driverRepository.updateDriverAutoById(auto, id);
    }

    @Override
    public DriverEntity updateDriverRequestById(RequestEntity request, Long id) {
        return driverRepository.updateDriverRequestById(request, id);
    }

    @Override
    public void updateDriverDoneDestination(Long id) {
        driverRepository.updateRequestIsDone(id);
        driverRepository.updateDriverDoneDestination(id);
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }


}
