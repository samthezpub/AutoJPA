package com.example.autojpa.Service.impl;

import com.example.autojpa.Entity.RequestEntity;
import com.example.autojpa.Repository.RequestRepository;
import com.example.autojpa.Service.RequestService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public RequestEntity saveRequest(RequestEntity request) {
        return requestRepository.save(request);
    }

    @Override
    public List<RequestEntity> findAll() {
        return requestRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Optional<RequestEntity> findById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public Optional<List<RequestEntity>> findAllNotDoneRequestsAndFree() {
        return requestRepository.findAllNotDoneRequestsAndFree();
    }

    @Override
    public RequestEntity updateRequest(RequestEntity request) {
        return requestRepository.save(request);
    }



    @Override
    public void deleteById(Long id) {
        requestRepository.deleteById(id);
    }
}
