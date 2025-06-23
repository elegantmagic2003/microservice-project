package com.vti.department_service.controller;

import com.vti.department_service.dto.DepartmentDTO;
import com.vti.department_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping()
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
}
