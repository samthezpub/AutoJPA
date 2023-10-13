package com.example.autojpa.Service;

import com.example.autojpa.Entity.DestinationEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DestinationService {
    List<DestinationEntity> findAll();
    Optional<DestinationEntity> findDestinationById(long id);
}
