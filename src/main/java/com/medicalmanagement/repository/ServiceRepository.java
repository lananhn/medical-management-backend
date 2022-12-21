package com.medicalmanagement.repository;

import com.medicalmanagement.entity.ServiceEntity;
import com.medicalmanagement.services.dto.response.ServiceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceProjection> findByOrderByNameAsc();
    @Query(nativeQuery = true, value = "SELECT * FROM services s WHERE lower(s.name) AND s.type_of_service_id LIKE lower(CONVERT(lower(?1), BINARY)) AND s.type_of_service_id")
    List<ServiceEntity> findServiceByNameAndIdType(Long idType, String name);
}
