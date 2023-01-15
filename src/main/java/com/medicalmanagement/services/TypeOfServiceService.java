package com.medicalmanagement.services;

import com.medicalmanagement.constants.Status;
import com.medicalmanagement.entity.TypeOfService;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.TypeOfServiceRepository;
import com.medicalmanagement.services.dto.response.TypeOfServiceDto;
import com.medicalmanagement.services.dto.request.TypeOfServiceDTO;
import com.medicalmanagement.services.dto.request.ListTypeOfServiceDTO;
import com.medicalmanagement.services.dto.response.TypeOfServiceProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TypeOfServiceService {
    private final TypeOfServiceRepository typeOfServiceRepository;
    public TypeOfService findById(Long id) {
        TypeOfService typeOfService = typeOfServiceRepository.findById(id).orElseThrow(() ->
                new Exception("Chưa tồn tại thông tin của dịch vụ"));
        return typeOfService;
    }
    @Transactional
    public List<TypeOfService> add(ListTypeOfServiceDTO name) {
        List<TypeOfService> typeOfServices = new ArrayList<>();
        name.getName().forEach(n -> {
            if (typeOfServiceRepository.findServiceByName(n).isEmpty()) {
                TypeOfService typeOfService = new TypeOfService();
                typeOfService.setName(n);
                typeOfService.setStatus(Status.ACTIVE.getId());
                typeOfServices.add(typeOfService);
            }
        });
        typeOfServiceRepository.saveAll(typeOfServices);
        return typeOfServices;
    }
    @Transactional
    public List<TypeOfServiceProjection> list() {
        List<TypeOfServiceProjection> listTypeService = typeOfServiceRepository.findByOrderByNameAsc();
        return listTypeService;
    }
    @Transactional
    public TypeOfServiceDTO update(Long id, TypeOfServiceDTO dto) {
        TypeOfService typeOfService = findById(id);
        typeOfService.setName(dto.getName());
        typeOfServiceRepository.save(typeOfService);
        return dto;
    }
    @Transactional
    public void updateStatus(Long id) {
        TypeOfService typeOfService = findById(id);
        if (typeOfService.getStatus().equals(Status.ACTIVE.getId())) {
            typeOfService.setStatus(Status.INACTIVE.getId());
            typeOfServiceRepository.save(typeOfService);
        } else {
            typeOfService.setStatus(Status.ACTIVE.getId());
            typeOfServiceRepository.save(typeOfService);
        }
    }
    @Transactional
    public TypeOfServiceDto findTypeById(Long id) {
        TypeOfService typeOfService = findById(id);
        TypeOfServiceDto dto = new TypeOfServiceDto();
        dto.setId(typeOfService.getId());
        dto.setName(typeOfService.getName());
        return dto;
    }
}
