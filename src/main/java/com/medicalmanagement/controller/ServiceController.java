package com.medicalmanagement.controller;

import com.medicalmanagement.services.ServiceService;
import com.medicalmanagement.services.dto.ServiceDTO;
import com.medicalmanagement.services.dto.response.ServiceProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService service;

    @GetMapping("")
    private ResponseEntity<List<ServiceProjection>> list() {
        List<ServiceProjection> listServices = service.list();
        return ResponseEntity.ok(listServices);
    }

    @PostMapping("/add")
    private ResponseEntity<ServiceDTO> add(@RequestBody @Valid ServiceDTO dto) {
        service.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{idService}")
    private ResponseEntity<ServiceDTO> update(@PathVariable Long idService, @RequestBody @Valid ServiceDTO dto) {
        service.update(idService, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/updateStatus/{id}")
    private ResponseEntity<String> updateStatus(@PathVariable Long id) {
        service.updateStatus(id);
        return ResponseEntity.ok("Cap nhat thanh cong");
    }
}
