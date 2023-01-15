package com.medicalmanagement.controller;

import com.medicalmanagement.services.ResultsExamService;
import com.medicalmanagement.services.dto.ResultsExamDTO;
import com.medicalmanagement.services.dto.response.ResultsExamProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results-exam")
public class ResultsExaminationController {
    private final ResultsExamService resultsExamService;

    @GetMapping("")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<ResultsExamProjection>> listResults() {
        return ResponseEntity.ok(resultsExamService.listResults());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ResultsExamDTO> add(@RequestBody @Valid ResultsExamDTO dto) {
        resultsExamService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<String> update(@RequestBody @Valid ResultsExamDTO dto, @PathVariable Long id) {
        resultsExamService.update(id, dto);
        return ResponseEntity.ok("Cap nhat thanh cong");
    }
}
