package com.timeTrackApplication.timeTrackApplication.Repository;

import com.timeTrackApplication.timeTrackApplication.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
