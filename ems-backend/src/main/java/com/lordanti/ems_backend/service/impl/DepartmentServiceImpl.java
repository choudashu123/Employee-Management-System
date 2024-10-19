package com.lordanti.ems_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lordanti.ems_backend.dto.DepartmentDto;
import com.lordanti.ems_backend.entity.Department;
import com.lordanti.ems_backend.exception.ResourceNotFoundException;
import com.lordanti.ems_backend.mapper.DepartmentMapper;
import com.lordanti.ems_backend.repository.DepartmentRepository;
import com.lordanti.ems_backend.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto createDepartment (DepartmentDto departmentDto){

        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment  = departmentRepository.save(department);
        return DepartmentMapper.maptoDepartmentDto(savedDepartment);
    }
    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
        new ResourceNotFoundException("Department does not exist with given id : "+ departmentId)
        );
        return DepartmentMapper.maptoDepartmentDto(department);
        }
    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map((department) -> 
        DepartmentMapper.maptoDepartmentDto(department)).collect(Collectors.toList());
    }
    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
            () -> new ResourceNotFoundException("Department does not exists with the given id : "+ departmentId)
            );
            department.setDepartmentName(updatedDepartment.getDepartmentName());
            department.setDepartmentdescription(updatedDepartment.getDepartmentdescription());

            Department savedDepartment = departmentRepository.save(department);
            
            return DepartmentMapper.maptoDepartmentDto(savedDepartment);
    }
    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(
            () -> new ResourceNotFoundException("Department does not exists with such department id : "+departmentId)
        );
        departmentRepository.deleteById(departmentId);
    }

}
