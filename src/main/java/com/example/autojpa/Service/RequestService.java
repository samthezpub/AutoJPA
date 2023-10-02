package com.example.autojpa.Service;

import com.example.autojpa.Entity.AutoEntity;
import com.example.autojpa.Entity.RequestEntity;

import java.util.Optional;

public interface RequestService {
    RequestEntity saveRequest(RequestEntity request);

    Optional<RequestEntity> findById(Long id);

    RequestEntity updateRequest(RequestEntity request);

    void deleteById(Long id);
}
