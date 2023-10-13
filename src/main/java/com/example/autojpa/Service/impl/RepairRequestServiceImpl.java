package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.RepairRequestEntity;
import com.example.autojpa.Repository.RepairRequestRepository;
import com.example.autojpa.Service.RepairRequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairRequestServiceImpl implements RepairRequestService {
    RepairRequestRepository repairRequestRepository;

    public RepairRequestServiceImpl(RepairRequestRepository repairRequestRepository) {
        this.repairRequestRepository = repairRequestRepository;
    }

    @Override
    public RepairRequestEntity addRequest(RepairRequestEntity request) {
        return repairRequestRepository.save(request);
    }

    @Override
    public List<RepairRequestEntity> findAll() {
        return repairRequestRepository.findAll();
    }

    @Override
    public Optional<RepairRequestEntity> findById(long id) {
        return repairRequestRepository.findById(id);
    }

    @Override
    public RepairRequestEntity updateRequest(RepairRequestEntity request) {
        return repairRequestRepository.save(request);
    }

    @Override
    public void deleteRequestById(Long id) {
        repairRequestRepository.deleteById(id);
    }
}
