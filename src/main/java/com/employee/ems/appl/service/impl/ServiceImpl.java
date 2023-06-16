package com.employee.ems.appl.service.impl;




import com.employee.ems.appl.entity.Department;
import com.employee.ems.appl.entity.Employee;
import com.employee.ems.appl.repository.DepartmentRepository;
import com.employee.ems.appl.repository.EmployeeRepository;
import com.employee.ems.appl.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceImpl implements UserService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    public ServiceImpl(EmployeeRepository employeeRepository,DepartmentRepository departmentRepository) {
        super();
        this.employeeRepository = employeeRepository;
        this.departmentRepository=departmentRepository;
    }

    @Override
    public List<Employee> getAllEmployees(){

        return employeeRepository.findAll();
    }
    @Override
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }
    @Override
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    @Override
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).get();
    }
    @Override
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    @Override
    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }

}