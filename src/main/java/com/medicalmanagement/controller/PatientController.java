package com.medicalmanagement.controller;

import com.medicalmanagement.services.PatientService;
import com.medicalmanagement.services.dto.PatientDTO;
import com.medicalmanagement.services.dto.response.PatientProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("")
    @PreAuthorize("hasRole('NURSE') or hasRole('ACCOUNTANT')")
    public ResponseEntity<List<PatientProjection>> list() {
        List<PatientProjection> list = patientService.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<PatientDTO> add(@RequestBody @Valid PatientDTO dto) {
        patientService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<PatientDTO> update(@PathVariable Long id, @RequestBody @Valid PatientDTO dto) {
        patientService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/insurance/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<String> updateInsurance(@PathVariable long id) {
        patientService.updateInsurance(id);
        return ResponseEntity.ok("Cập nhật thành công");
    }
}
