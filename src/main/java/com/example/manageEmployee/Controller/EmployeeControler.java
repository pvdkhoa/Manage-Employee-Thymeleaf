package com.example.manageEmployee.Controller;

import com.example.manageEmployee.Repository.EmployeeRepository;
import com.example.manageEmployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EmployeeControler {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping({"/showEmployees","/list"})
    public ModelAndView showEmployees(){
        ModelAndView modelAndView = new ModelAndView("list-employees");
        List<Employee> list = employeeRepository.findAll();
        modelAndView.addObject("employees",list);
        return  modelAndView;
    }

    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm(){
        ModelAndView modelAndView = new ModelAndView("add-employee-form");
        Employee newEmployee = new Employee();
        modelAndView.addObject("employee",newEmployee);
        return modelAndView;
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return new ModelAndView("redirect:/list");
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId){
        ModelAndView modelAndView = new ModelAndView("add-employee-form");
        Employee employee = employeeRepository.findById(employeeId).get();
        modelAndView.addObject("employee",employee);
        return modelAndView;
    }

    @GetMapping("/deleteEmployee")
    public ModelAndView deleteEmployee(@RequestParam Long employeeId){
        employeeRepository.deleteById(employeeId);
        return new ModelAndView("redirect:/list");
    }
}
