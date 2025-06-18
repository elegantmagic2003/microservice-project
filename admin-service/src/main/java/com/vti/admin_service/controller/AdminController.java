package com.vti.admin_service.controller;

import com.vti.admin_service.dto.DepartmentDTO;
import com.vti.admin_service.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/departments")
    public List<DepartmentDTO> getDepartments() {
        return adminService.getDepartments();
    }
}
