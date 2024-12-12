package com.fmattaperdomo.rh_app.services;

import com.fmattaperdomo.rh_app.models.Employee;
import com.fmattaperdomo.rh_app.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee searchEmployeeById(Integer idEmployee) {
        Employee employee = employeeRepository.findById((long) idEmployee).orElse(null);
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }
}
