package com.lordanti.ems_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lordanti.ems_backend.dto.DepartmentDto;

@Service
public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long departmentId);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment);

    void deleteDepartment(Long departmentId);
}
