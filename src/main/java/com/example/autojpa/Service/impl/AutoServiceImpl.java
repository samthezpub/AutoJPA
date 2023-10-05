package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.CargoTypeEntity;
import com.example.autojpa.Exception.NotFindException;
import com.example.autojpa.Repository.AutoRepository;
import com.example.autojpa.Service.AutoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutoServiceImpl implements AutoService {


    private final AutoRepository autoRepository;

    public AutoServiceImpl(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    @Override
    public AutoEntity saveAuto(AutoEntity auto) {
        return autoRepository.save(auto);
    }

    @Override
    public Optional<AutoEntity> findById(Long id) {
        return autoRepository.findById(id);
    }

    @Override
    public AutoEntity findAutoEntityByWeight(Double weight) {
        try {
            if (autoRepository.findAutoEntityByWeight(weight).get().size() > 0){
                return autoRepository.findAutoEntityByWeight(weight)
                        .get()
                        .get(0);
            }
            throw new NotFindException("Машинка с такой грузоподъемностью не найдена!");

        } catch (NotFindException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public AutoEntity updateAuto(AutoEntity auto) {
        return autoRepository.save(auto);
    }

    @Override
    public void updateCargoTypeById(Long id, CargoTypeEntity cargoType) {
        autoRepository.updateCargoTypeById(id, cargoType);
    }

    @Override
    public void deleteById(Long id) {
        autoRepository.deleteById(id);
    }
}
