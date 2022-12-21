package com.medicalmanagement.repository;

import com.medicalmanagement.constants.ERole;
import com.medicalmanagement.entity.Role;
import com.medicalmanagement.services.dto.response.RoleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    List<RoleProjection> findByOrderByNameAsc();
}
