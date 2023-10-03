package com.example.autojpa.Service;


import com.example.autojpa.Entity.RepairRequestEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RepairRequestService {


    RepairRequestEntity addRequest(RepairRequestEntity request);
    List<RepairRequestEntity> findAll();
    RepairRequestEntity updateRequest(RepairRequestEntity request);
    void deleteRequestById(Long id);

}
