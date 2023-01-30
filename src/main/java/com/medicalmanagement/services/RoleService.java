package com.medicalmanagement.services;

import com.medicalmanagement.constants.Status;
import com.medicalmanagement.entity.Role;
import com.medicalmanagement.exceptions.Exception;
import com.medicalmanagement.repository.RoleRepository;
import com.medicalmanagement.services.dto.RoleDTO;
import com.medicalmanagement.services.dto.response.RoleProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() ->
                new Exception("Role chưa tồn tại"));
        return role;
    }
    @Transactional
    public List<RoleProjection> list() {
        List<RoleProjection> listRole = roleRepository.listRoles();
        return listRole;
    }
    @Transactional
    public RoleDTO add(RoleDTO dto) {
        Role role = new Role();
        role.setName(dto.getName());
        roleRepository.save(role);
        return dto;
    }
    @Transactional
    public RoleDTO update(Long id, RoleDTO dto) {
        Role role = findById(id);
        role.setName(dto.getName());
        roleRepository.save(role);
        return dto;
    }
}
