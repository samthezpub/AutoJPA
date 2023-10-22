package com.example.autojpa.Repository;

import com.example.autojpa.Entity.RoleEntity;
import com.example.autojpa.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u.roles FROM UserEntity u WHERE u.id=:id")
    List<String> getRoleNames(@Param("id") Long userId);
}
