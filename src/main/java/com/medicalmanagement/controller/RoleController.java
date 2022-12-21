package com.medicalmanagement.controller;

import com.medicalmanagement.services.RoleService;
import com.medicalmanagement.services.dto.RoleDTO;
import com.medicalmanagement.services.dto.response.RoleProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("")
    private ResponseEntity<List<RoleProjection>> list() {
        List<RoleProjection> listRoles = roleService.list();
        return ResponseEntity.ok(listRoles);
    }

    @PostMapping("/add")
    private ResponseEntity<RoleDTO> add(RoleDTO dto) {
        roleService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<RoleDTO> update(@PathVariable Long id, RoleDTO dto) {
        roleService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update-status/{id}")
    private ResponseEntity<String> updateStatus(@PathVariable Long id) {
        roleService.updateStatus(id);
        return ResponseEntity.ok("Cập nhật thành công");
    }
}
