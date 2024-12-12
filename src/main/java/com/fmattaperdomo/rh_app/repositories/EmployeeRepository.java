package com.fmattaperdomo.rh_app.repositories;

import com.fmattaperdomo.rh_app.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
