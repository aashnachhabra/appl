package com.employee.ems.appl;

import com.employee.ems.appl.entity.Employee;
import com.employee.ems.appl.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception{
//          Employee employee1 = new Employee("Shakin","Sha","ss@gmail.com");
//      employeeRepository.save(employee1);
//
//     Employee employee2 = new Employee("xxx","yyy","xs@gmail.com");
//    employeeRepository.save(employee2);
//
//    Employee employee3 = new Employee("Tony","Stark","ts@gmail.com");
//    employeeRepository.save(employee3);
    }
}