package com.medicalmanagement.controller;

import com.medicalmanagement.services.ResultsExamService;
import com.medicalmanagement.services.dto.ResultsExamDTO;
import com.medicalmanagement.services.dto.response.ResultsExamProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results-exam")
public class ResultsExaminationController {
    private final ResultsExamService resultsExamService;

    @GetMapping("")
    private ResponseEntity<List<ResultsExamProjection>> listResults() {
        return ResponseEntity.ok(resultsExamService.listResults());
    }

    @PostMapping("/add")
    private ResponseEntity<ResultsExamDTO> add(@RequestBody @Valid ResultsExamDTO dto) {
        resultsExamService.add(dto);
        return ResponseEntity.ok(dto);
    }
}
