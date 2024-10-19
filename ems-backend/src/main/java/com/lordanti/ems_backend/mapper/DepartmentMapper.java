package com.lordanti.ems_backend.mapper;

import com.lordanti.ems_backend.dto.DepartmentDto;
import com.lordanti.ems_backend.entity.Department;

public class DepartmentMapper {
    public static DepartmentDto maptoDepartmentDto(Department department){
        return new DepartmentDto(
            department.getId(),
            department.getDepartmentName(),
            department.getDepartmentdescription()
        );
    }
    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
            departmentDto.getId(),
            departmentDto.getDepartmentName(),
            departmentDto.getDepartmentdescription()
        );
    }

}
