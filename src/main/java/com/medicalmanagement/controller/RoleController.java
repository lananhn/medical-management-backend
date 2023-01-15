package com.medicalmanagement.controller;

import com.medicalmanagement.services.RoleService;
import com.medicalmanagement.services.dto.RoleDTO;
import com.medicalmanagement.services.dto.response.RoleProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<List<RoleProjection>> list() {
        List<RoleProjection> listRoles = roleService.list();
        return ResponseEntity.ok(listRoles);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<RoleDTO> add(RoleDTO dto) {
        roleService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<RoleDTO> update(@PathVariable Long id, RoleDTO dto) {
        roleService.update(id, dto);
        return ResponseEntity.ok(dto);
    }
}
