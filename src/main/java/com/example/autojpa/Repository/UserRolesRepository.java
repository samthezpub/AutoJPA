package com.example.autojpa.Repository;

import com.example.autojpa.Entity.RoleEntity;
import com.example.autojpa.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<RoleEntity, Long> {
    @Query("SELECT r.name FROM RoleEntity r JOIN r.users u WHERE u.id=:userId")
    List<String> getRoleNames(@Param("userId") Long userId);
}

