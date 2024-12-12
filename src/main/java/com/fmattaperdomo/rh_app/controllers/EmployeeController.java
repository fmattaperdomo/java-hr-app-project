package com.fmattaperdomo.rh_app.controllers;

import com.fmattaperdomo.rh_app.exceptions.ResourceNotFoundException;
import com.fmattaperdomo.rh_app.models.Employee;
import com.fmattaperdomo.rh_app.services.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rh-app")
@CrossOrigin("http:/localhost:3000")
public class EmployeeController {
    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService employeeService;

    // http://localhost:8080/rh-app/employees
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        var employees = employeeService.listEmployees();
        employees.forEach((employee -> logger.info(employee.toString())));
        return employees;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        logger.info("Employee to add: " + employee);
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee>
    getEmployeeById(@PathVariable Integer id){
        Employee employee = employeeService.searchEmployeeById(id);
        if(employee == null)
            throw new ResourceNotFoundException("Id Not found: " + id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee>
    updateEmployee(@PathVariable Integer id,
                       @RequestBody Employee employeeReceived){
        Employee employee = employeeService.searchEmployeeById(id);
        if (employee == null)
            throw new ResourceNotFoundException("The id received not exists: " + id);
        //employee.setName(employeeReceived.getName());
        //employee.setDepartment(employeeReceived.getDepartment());
        //employee.setSalary(employeeReceived.getSalary());
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>>
    deleteEmployee(@PathVariable Integer id){
        Employee employee = employeeService.searchEmployeeById(id);
        if(employee == null)
            throw new ResourceNotFoundException("The id received don't exist: " + id);
        employeeService.deleteEmployee(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
