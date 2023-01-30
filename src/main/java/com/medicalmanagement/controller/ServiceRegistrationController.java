package com.medicalmanagement.controller;

import com.medicalmanagement.entity.ServiceRegistration;
import com.medicalmanagement.services.ServiceRegistrationService;
import com.medicalmanagement.services.dto.UpdateRegDTO;
import com.medicalmanagement.services.dto.request.AddRegDTO;
import com.medicalmanagement.services.dto.response.RegistrationDto;
import com.medicalmanagement.services.dto.response.ServiceRegistraitonProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/registration")
@RequiredArgsConstructor
public class ServiceRegistrationController {
    private final ServiceRegistrationService service;

    @GetMapping("")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<ServiceRegistraitonProjection>> list() {
        List<ServiceRegistraitonProjection> list = service.listReg();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AddRegDTO> add(@RequestBody @Valid AddRegDTO dto) {
        service.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<UpdateRegDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateRegDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/listServiceRegis/{patientId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<ServiceRegistration>> listServiceByPatient(@PathVariable Long patientID) {
        return ResponseEntity.ok(service.listServiceByPatient(patientID));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<RegistrationDto> getRegById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findRegById(id));
    }
}
