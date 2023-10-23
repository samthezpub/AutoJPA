package com.example.autojpa.Repository;

import com.example.autojpa.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("FROM UserEntity u WHERE u.userName=:username")
    Optional<UserEntity> findUserAccount(@Param("username") String username);
}
