package com.example.autojpa.Service;

import com.example.autojpa.Entity.CargoTypeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CargoTypeService {
    List<CargoTypeEntity> findAll();
    Optional<CargoTypeEntity> findById(Long id);
}
