package com.vti.admin_service.services;

import com.vti.admin_service.dto.DepartmentDTO;
import com.vti.admin_service.feignclient.DepartmentFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final DepartmentFeignClient departmentFeignClient;

    public List<DepartmentDTO> getDepartments() {
        return departmentFeignClient.getAllDepartments();
    }
}
