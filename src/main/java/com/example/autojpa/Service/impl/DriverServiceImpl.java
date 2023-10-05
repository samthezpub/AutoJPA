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
import java.util.Random;

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
    public Optional<DriverEntity> findDriverByExperienceAndFree(Integer experience) {
        return  driverRepository.findIdByExperienceAndFree(experience);
    }

    @Override
    public Optional<DriverEntity> findDriverEntityByRequestId(Long request_id) {
        return driverRepository.findDriverEntityByRequestId(request_id);
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

        Random random = new Random();
        updateDriverMoneyById(id, random.nextDouble(100, 2000));
    }

    @Override
    public void updateDriverMoneyById(Long id, double money) {
        driverRepository.updateDriverMoneyById(id, money);
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public void reportArrival(Long driverId) {
        Optional<DriverEntity> optionalDriver = driverRepository.findById(driverId);

        if (optionalDriver.isPresent()){
            DriverEntity driver = optionalDriver.get();

            driver.setRequest(null);
            driver.setAuto(null);

            Random random = new Random();
            driver.setMoney(driver.getMoney() + random.nextDouble(200, 2000));
        }

    }


}
