package com.medicalmanagement.repository;

import com.medicalmanagement.entity.TypeOfService;
import com.medicalmanagement.services.dto.response.TypeOfServiceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeOfServiceRepository extends JpaRepository<TypeOfService, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_of_service tfs WHERE lower(tfs.name) LIKE lower(CONVERT(lower(?1), BINARY)) ")
    List<TypeOfService> findServiceByName(String name);
    List<TypeOfServiceProjection> findByOrderByNameAsc();
}
