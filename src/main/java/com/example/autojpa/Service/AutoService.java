package com.example.autojpa.Service;

import com.example.autojpa.Entity.AutoEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AutoService {
    AutoEntity saveAuto(AutoEntity auto);

    Optional<AutoEntity> findById(Long id);

    AutoEntity updateAuto(AutoEntity auto);

    void deleteById(Long id);
}
