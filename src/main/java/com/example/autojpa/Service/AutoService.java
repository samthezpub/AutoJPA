package com.example.autojpa.Service;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.CargoTypeEntity;
import com.example.autojpa.Exception.NotFindException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AutoService {
    AutoEntity saveAuto(AutoEntity auto);

    Optional<AutoEntity> findById(Long id);

    AutoEntity findAutoEntityByWeight(@Param("weight") Double weight) throws NotFindException;

    AutoEntity updateAuto(AutoEntity auto);
    void updateCargoTypeById(Long id, CargoTypeEntity cargoType);

    void deleteById(Long id);
}
