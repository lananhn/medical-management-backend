package com.medicalmanagement.controller;

import com.medicalmanagement.services.PatientService;
import com.medicalmanagement.services.dto.PatientDTO;
import com.medicalmanagement.services.dto.response.PatientProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("")
    private ResponseEntity<List<PatientProjection>> list() {
        List<PatientProjection> list = patientService.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    private ResponseEntity<PatientDTO> add(@RequestBody @Valid PatientDTO dto) {
        patientService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<PatientDTO> update(@PathVariable Long id, @RequestBody @Valid PatientDTO dto) {
        patientService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/insurance/{id}")
    private ResponseEntity<String> updateInsurance(@PathVariable long id) {
        patientService.updateInsurance(id);
        return ResponseEntity.ok("Cập nhật thành công");
    }
}
