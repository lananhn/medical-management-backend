package com.medicalmanagement.services;

import com.medicalmanagement.constants.Status;
import com.medicalmanagement.entity.ServiceEntity;
import com.medicalmanagement.entity.TypeOfService;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.ServiceRepository;
import com.medicalmanagement.repository.TypeOfServiceRepository;
import com.medicalmanagement.services.dto.request.ServiceDTO;
import com.medicalmanagement.services.dto.response.ServiceDto;
import com.medicalmanagement.services.dto.response.ServiceProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final TypeOfServiceRepository typeOfServiceRepository;
    @Transactional
    public List<ServiceProjection> list() {
        List<ServiceProjection> listServices = serviceRepository.findByOrderByNameAsc();
        return listServices;
    }
    @Transactional
    public ServiceDTO add(ServiceDTO dto) {
        ServiceEntity entity = new ServiceEntity();
        entity.setName(dto.getName());
        entity.setUnit(dto.getUnit());
        entity.setPrice(dto.getPrice());
        entity.setStatus(Status.ACTIVE.getId());
        TypeOfService type = typeOfServiceRepository.findById(dto.getTypeOfService()).orElseThrow(() ->
                new Exception("Loai dich vu chua ton tai"));
        entity.setTypeOfService(type);
        serviceRepository.save(entity);
        return dto;
    }
    @Transactional
    public ServiceDTO update(Long id, ServiceDTO dto) {
        ServiceEntity entity = serviceRepository.findById(id).orElseThrow(() ->
                new Exception("Dich vu chua ton tai"));
        TypeOfService typeOfService = typeOfServiceRepository.findById(dto.getTypeOfService()).orElseThrow(() ->
                new Exception("Loai dich vu chua ton tai"));
        entity.setName(dto.getName());
        entity.setUnit(dto.getUnit());
        entity.setPrice(dto.getPrice());
        entity.setTypeOfService(typeOfService);
        serviceRepository.save(entity);
        return dto;
    }
    @Transactional
    public void updateStatus(Long id) {
        ServiceEntity entity = serviceRepository.findById(id).orElseThrow(() ->
                new Exception("Loai dich vu chua ton tai"));
        if (entity.getStatus().equals(Status.ACTIVE.getId())) {
            entity.setStatus(Status.INACTIVE.getId());
            serviceRepository.save(entity);
        } else {
            entity.setStatus(Status.ACTIVE.getId());
            serviceRepository.save(entity);
        }
    }
    public ServiceDto findServiceById(Long id, Long idType) {
        ServiceEntity entity = serviceRepository.findById(id).orElseThrow(() ->
                new Exception("Chưa tồn tại thông tin của dịch vụ"));
        TypeOfService typeOfService = typeOfServiceRepository.findById(idType).orElseThrow(() ->
                new Exception("Chưa tồn tại thông tin của loại dịch vụ"));
        if (entity.getTypeOfService().getId() != typeOfService.getId()) {
            throw new Exception("Dịch vụ không thuộc trong các loại dịch vụ");
        }
        ServiceDto dto = new ServiceDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUnit(entity.getUnit());
        dto.setPrice(entity.getPrice());
        dto.setStatus(entity.getStatus());
        dto.setTypeOfService(typeOfService.getId());
        return dto;
    }

}
