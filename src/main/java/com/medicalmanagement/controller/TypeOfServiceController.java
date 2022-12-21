package com.medicalmanagement.controller;

import com.medicalmanagement.services.TypeOfServiceService;
import com.medicalmanagement.services.dto.TypeOfServiceDTO;
import com.medicalmanagement.services.dto.request.ListTypeOfServiceDTO;
import com.medicalmanagement.services.dto.response.TypeOfServiceProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type-of-service")
public class TypeOfServiceController {

    private final TypeOfServiceService typeOfServiceService;

    @PostMapping("/add")
    public ResponseEntity<ListTypeOfServiceDTO> add(@RequestBody @Valid ListTypeOfServiceDTO dto) {
        typeOfServiceService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<TypeOfServiceProjection>> list() {
        List<TypeOfServiceProjection> listTypeService = typeOfServiceService.list();
        return ResponseEntity.ok(listTypeService);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid TypeOfServiceDTO dto) {
        typeOfServiceService.update(id, dto);
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @PutMapping("/updateStatus/{id}")
    private ResponseEntity<String> updateStatus(@PathVariable Long id) {
        typeOfServiceService.updateStatus(id);
        return ResponseEntity.ok("Cập nhật thành công");
    }
}
