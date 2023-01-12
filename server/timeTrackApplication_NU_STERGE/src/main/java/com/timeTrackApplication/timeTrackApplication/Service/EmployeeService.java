package com.timeTrackApplication.timeTrackApplication.Service;

import com.timeTrackApplication.timeTrackApplication.Model.Employee;
import com.timeTrackApplication.timeTrackApplication.Repository.EmployeeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /* /---------------------------POST---------------------------------------------------/ */

    // POST: add new employee
    public Employee createEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    /* /---------------------------GET----------------------------------------------------/ */

    // GET: show all  employee
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    // GET: show employees that worked a specific amount of time
    public List<Employee> getEmployeeByHourlyRate(String hourlyRate) {
        List<Employee> employeeList = getAllEmployees();
        List<Employee> returnedEmployeeList = new ArrayList<>();

        for (Employee employee : employeeList) {
            if (employee.getHourlyRate().equals(hourlyRate)) {
                returnedEmployeeList.add(employee);
            }
        }

        return returnedEmployeeList;
    }

    // GET: show all employee with a specific name
    public List<Employee> getEmployeeByName(String name) {
        List<Employee> employeeList = getAllEmployees();
        List<Employee> returnedEmployeeList = new ArrayList<>();

        for (Employee employee : employeeList) {
            if (employee.getName().equals(name)) {
                returnedEmployeeList.add(employee);
            }
        }

        return returnedEmployeeList;
    }

    // GET: show employee with an ID
    public List<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findAllById(Collections.singleton(id));
    }


    /* /---------------------------------------DELETE------------------------------------------/ */

    // DELETE: delete a employee body request
    public void deleteEmployee(Employee employee) {

        employeeRepository.delete(employee);
    }

    // DELETE: delete a employee by name
    public void deleteEmployeeByName(String name) {
        //employeeRepository.delete(name);

        List<Employee> employeeList = getAllEmployees();
        for (Employee employee : employeeList) {
            if (employee.getName().equals(name)) {
                deleteEmployee(employee);
            }
        }
    }

    // DELETE: delete a employee by id
    public void deleteEmployeeById(@NotNull Employee employee) {
        employeeRepository.deleteById(employee.getId());
    }


    /* /------------------------------------------PUT-------------------------------------------/ */

    // SAVE operation
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // PUT: update an employee name
    public Employee updateEmployeeDetails(Integer id, Employee employee) {
        Employee employeeToUpdate = employeeRepository.findById(id).get();

        if (employee.getName().equals(employee.getName())) {
            employeeToUpdate.setName((employee.getName()));
        }
        if (employee.getHourlyRate().equals(employee.getHourlyRate())) {
            employeeToUpdate.setHourlyRate((employee.getHourlyRate()));
        }

        Employee employee1 = saveEmployee(employeeToUpdate);
        return employee1;
    }
}
