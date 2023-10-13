package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.CargoTypeEntity;
import com.example.autojpa.Repository.CargoTypeRepository;
import com.example.autojpa.Service.CargoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoTypeServiceImpl implements CargoTypeService {
    private final CargoTypeRepository cargoTypeRepository;

    @Autowired
    public CargoTypeServiceImpl(CargoTypeRepository cargoTypeRepository) {
        this.cargoTypeRepository = cargoTypeRepository;
    }

    @Override
    public List<CargoTypeEntity> findAll() {
        return cargoTypeRepository.findAll();
    }

    @Override
    public Optional<CargoTypeEntity> findById(Long id) {
        return cargoTypeRepository.findById(id);
    }
}
