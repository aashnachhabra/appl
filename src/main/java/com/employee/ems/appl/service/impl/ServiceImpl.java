package com.employee.ems.appl.service.impl;




import com.employee.ems.appl.dto.UserDto;
import com.employee.ems.appl.entity.Department;
import com.employee.ems.appl.entity.Employee;
import com.employee.ems.appl.entity.Role;
import com.employee.ems.appl.entity.User;
import com.employee.ems.appl.repository.DepartmentRepository;
import com.employee.ems.appl.repository.EmployeeRepository;
import com.employee.ems.appl.repository.RoleRepository;
import com.employee.ems.appl.repository.UserRepository;
import com.employee.ems.appl.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceImpl implements UserService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    private UserRepository userRepository;
private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    public ServiceImpl(EmployeeRepository employeeRepository,DepartmentRepository departmentRepository, UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        super();
        this.employeeRepository = employeeRepository;
        this.departmentRepository=departmentRepository;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
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

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        System.out.println(user);
//         encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }


    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }


    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}