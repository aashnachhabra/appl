package com.employee.ems.appl.repository;

import com.employee.ems.appl.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
