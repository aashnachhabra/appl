
package com.employee.ems.appl.controller;


import com.employee.ems.appl.entity.Department;
import com.employee.ems.appl.entity.Employee;
import com.employee.ems.appl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class EmployeeController {
    @Autowired
    private UserService userService;


    public EmployeeController(UserService userService) {
        super();
        this.userService = userService;
    }


    @GetMapping("/employees")
    public String listEmployees(Model model) {

        model.addAttribute("employees",userService.getAllEmployees());

        return "employees";
    }
    @GetMapping("/employees/new")
    public String createEmployee(Model model){
        //create student object to hold student form data
        Employee employee = new Employee();
        model.addAttribute("department",userService.getAllDepartment());
        model.addAttribute("employee",employee);
        return "create_employee";
    }
    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        userService.saveEmployee(employee);
        return "redirect:/employees";
    }
    @GetMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model){
        model.addAttribute("department",userService.getAllDepartment());
        model.addAttribute("employee",userService.getEmployeeById(id));
        return "edit_employee";
    }
    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model){
        //get employee from database by id
        Employee existingEmployee = userService.getEmployeeById(id);
        existingEmployee.setId(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmailId(employee.getEmailId());
        existingEmployee.setDeptName(employee.getDeptName());
        //save updated employee object
        userService.updateEmployee(existingEmployee);
        return "redirect:/employees";
    }
    @GetMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
        userService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
    @GetMapping("/employees/view/{id}")
    public String viewEmployee(@PathVariable Long id, Model model){
        model.addAttribute("employee",userService.getEmployeeById(id));
        return "view_employee";
    }
}