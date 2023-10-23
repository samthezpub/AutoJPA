package com.example.autojpa.Repository;

import com.example.autojpa.Entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    @Query("Select ur.role.name from UserRoleEntity ur where ur.appUser.Id = ?1")
    List<String> getRoleNames(Long userId);
}

