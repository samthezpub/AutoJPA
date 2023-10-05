package com.example.autojpa.Repository;

import com.example.autojpa.Entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long> {
    @Query("SELECT r FROM RequestEntity r  WHERE r.isDone=false AND r.isFree=true")
   Optional<List<RequestEntity>> findAllNotDoneRequestsAndFree();


}
