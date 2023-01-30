package com.medicalmanagement.controller;

import com.medicalmanagement.services.UserService;
import com.medicalmanagement.services.dto.request.UserDTO;
import com.medicalmanagement.services.dto.request.UpdateUserDTO;
import com.medicalmanagement.services.dto.response.UserDto;
import com.medicalmanagement.services.dto.response.UserProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserProjection>> listUser() {
        List<UserProjection> list = userService.listUser();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO userDTO) {
        userService.addUser(userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id) {
        userService.updateStatus(id);
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UpdateUserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDTO dto) {
        userService.updateUser(id, dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

//    @GetMapping("/role")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<>
}
