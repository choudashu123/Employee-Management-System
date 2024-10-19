package com.lordanti.ems_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lordanti.ems_backend.dto.EmployeeDto;
import com.lordanti.ems_backend.entity.Department;
import com.lordanti.ems_backend.entity.Employee;
import com.lordanti.ems_backend.exception.ResourceNotFoundException;
import com.lordanti.ems_backend.mapper.EmployeeMapper;
import com.lordanti.ems_backend.repository.DepartmentRepository;
import com.lordanti.ems_backend.repository.EmployeeRepository;
import com.lordanti.ems_backend.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override 
    public EmployeeDto createEmployee(EmployeeDto employeeDto){


        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with the following id : "+employeeDto.getDepartmentId()));

        employee.setDepartment(department); 
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Employee does not exist with given id : "+employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
            .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundException("Employee does not exist with the given id : "+employeeId)

        );
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        Department department = departmentRepository.findById(updateEmployee.getDepartmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Department does not exist with the following id : "+ updateEmployee.getDepartmentId()));

        employee.setDepartment(department); 
        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        employeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundException("Employee does not exist with the given id : "+employeeId)

        );
        employeeRepository.deleteById(employeeId);


    }



}
