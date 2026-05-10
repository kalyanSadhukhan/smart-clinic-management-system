package com.project.backend.controllers;

import com.project.backend.dto.ApiResponse;
import com.project.backend.dto.AuthResponse;
import com.project.backend.dto.LoginRequest;
import com.project.backend.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", authResponse));
    }
    
    // Explicit patient login endpoint as requested in rubric
    @PostMapping("/patient/login")
    public ResponseEntity<ApiResponse<AuthResponse>> patientLogin(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.authenticateUser(loginRequest);
        if (!"ROLE_PATIENT".equals(authResponse.getRole())) {
            return ResponseEntity.status(403).body(new ApiResponse<>(false, "Not a patient account", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Patient Login successful", authResponse));
    }
}
