package com.vti.department_service.service.impl;

import com.vti.department_service.dto.DepartmentDTO;
import com.vti.department_service.entity.Department;
import com.vti.department_service.repository.DepartmentRepository;
import com.vti.department_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    public final DepartmentRepository departmentRepository;
    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> DepartmentDTO.builder()
                        .name(department.getName())
                        .type(department.getType().toString())
                        .createdDate(department.getCreatedAt())
                        .build())
                .toList();
    }
}
