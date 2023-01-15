package com.medicalmanagement.controller;

import com.medicalmanagement.services.TypeOfServiceService;
import com.medicalmanagement.services.dto.response.TypeOfServiceDto;
import com.medicalmanagement.services.dto.request.TypeOfServiceDTO;
import com.medicalmanagement.services.dto.request.ListTypeOfServiceDTO;
import com.medicalmanagement.services.dto.response.TypeOfServiceProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type-of-service")
public class TypeOfServiceController {

    private final TypeOfServiceService typeOfServiceService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ListTypeOfServiceDTO> add(@RequestBody @Valid ListTypeOfServiceDTO dto) {
        typeOfServiceService.add(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN') or hasRole('NURSE')")
    public ResponseEntity<List<TypeOfServiceProjection>> list() {
        List<TypeOfServiceProjection> listTypeService = typeOfServiceService.list();
        return ResponseEntity.ok(listTypeService);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid TypeOfServiceDTO dto) {
        typeOfServiceService.update(id, dto);
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @PutMapping("/updateStatus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id) {
        typeOfServiceService.updateStatus(id);
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TypeOfServiceDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(typeOfServiceService.findTypeById(id));
    }
}
