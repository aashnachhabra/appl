package com.employee.ems.appl.service;



import com.employee.ems.appl.dto.UserDto;
import com.employee.ems.appl.entity.Department;
import com.employee.ems.appl.entity.Employee;
import com.employee.ems.appl.entity.User;

import java.util.List;

public interface UserService {
    List<Employee> getAllEmployees();
    List<Department> getAllDepartment();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(Long id);
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();


}