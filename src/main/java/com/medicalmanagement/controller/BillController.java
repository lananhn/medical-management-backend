package com.medicalmanagement.controller;

import com.medicalmanagement.entity.Bill;
import com.medicalmanagement.services.BillService;
import com.medicalmanagement.services.dto.BillDTO;
import com.medicalmanagement.services.dto.BillSumDTO;
import com.medicalmanagement.services.dto.response.BillProjection;
import com.medicalmanagement.services.dto.response.BillSumProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bill")
public class BillController {
    private final BillService service;

    @GetMapping("")
    private ResponseEntity<List<BillProjection>> list() {
        return ResponseEntity.ok(service.list());
    }

//    @GetMapping("/service-regis/{patientId}")
//    private ResponseEntity<List<Bill>> listBillSum(@PathVariable Long patientId) {
//        return ResponseEntity.ok(service.listBillSum(patientId));
//    }

    @PostMapping("/add")
    private ResponseEntity<BillDTO> add(@RequestBody @Valid BillDTO dto) {
        service.add(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update")
    private ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid BillDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok("Cap nhat thanh cong");
    }

    @PutMapping("/updateSum/{id}")
    private ResponseEntity<String> updateSum(@PathVariable Long id, @RequestBody @Valid BillSumDTO dto) {
        service.updateBillSum(id, dto);
        return ResponseEntity.ok("Cap nhat thanh cong");
    }

    @PutMapping("/updateStatusMoney/{id}")
    private ResponseEntity<String> updateStatusMoney(@PathVariable Long id) {
        service.updateStatusMoney(id);
        return ResponseEntity.ok("Cap nhat thanh cong");
    }
}
