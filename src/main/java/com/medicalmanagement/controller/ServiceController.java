package com.medicalmanagement.controller;

import com.medicalmanagement.entity.ServiceEntity;
import com.medicalmanagement.services.ServiceService;
import com.medicalmanagement.services.dto.request.ServiceDTO;
import com.medicalmanagement.services.dto.response.ServiceDto;
import com.medicalmanagement.services.dto.response.ServiceProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService service;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN') or hasRole('NURSE')")
    public ResponseEntity<List<ServiceProjection>> list() {
        List<ServiceProjection> listServices = service.list();
        return ResponseEntity.ok(listServices);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceDTO> add(@RequestBody @Valid ServiceDTO dto) {
        service.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceDTO> update(@PathVariable Long id, @RequestBody @Valid ServiceDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/updateStatus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id) {
        service.updateStatus(id);
        return ResponseEntity.ok("Cap nhat thanh cong");
    }

    @GetMapping("/{id}/type/{idType}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable(value = "id") Long id, @PathVariable(value = "idType") Long idType) {
        return ResponseEntity.ok(service.findServiceById(id, idType));
    }
}
