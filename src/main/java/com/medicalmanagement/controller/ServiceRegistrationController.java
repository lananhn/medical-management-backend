package com.medicalmanagement.controller;

import com.medicalmanagement.entity.ServiceRegistration;
import com.medicalmanagement.services.ServiceRegistrationService;
import com.medicalmanagement.services.dto.UpdateRegDTO;
import com.medicalmanagement.services.dto.request.AddRegDTO;
import com.medicalmanagement.services.dto.response.ServiceRegistraitonProjection;
import com.medicalmanagement.services.dto.response.ServiceRegistrationByPatient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/registration")
@RequiredArgsConstructor
public class ServiceRegistrationController {
    private final ServiceRegistrationService service;

    @GetMapping("")
    private ResponseEntity<List<ServiceRegistraitonProjection>> list() {
        List<ServiceRegistraitonProjection> list = service.listReg();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    private ResponseEntity<AddRegDTO> add(@RequestBody @Valid AddRegDTO dto) {
        service.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<UpdateRegDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateRegDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/listServiceRegis/{patientId}")
    private  ResponseEntity<List<ServiceRegistration>> listServiceByPatient(@PathVariable Long patientID) {
        return ResponseEntity.ok(service.listServiceByPatient(patientID));
    }
}
