package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.DestinationEntity;
import com.example.autojpa.Repository.DestinationRepository;
import com.example.autojpa.Service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<DestinationEntity> findAll() {
        return destinationRepository.findAll();
    }

    @Override
    public Optional<DestinationEntity> findDestinationById(long id) {
        return destinationRepository.findById(id);
    }
}
