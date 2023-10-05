package com.example.autojpa.Service;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.DriverEntity;
import com.example.autojpa.Entity.RequestEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RequestService {
    RequestEntity saveRequest(RequestEntity request);

    Optional<RequestEntity> findById(Long id);
    Optional<List<RequestEntity>> findAllNotDoneRequestsAndFree();

    RequestEntity updateRequest(RequestEntity request);

    void deleteById(Long id);
}
