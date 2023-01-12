package com.timeTrackApplication.timeTrackApplication.Controller;

import com.timeTrackApplication.timeTrackApplication.Model.Employee;
import com.timeTrackApplication.timeTrackApplication.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@ComponentScan

// all the CRUD operations

public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    /* /---------------------------------------POST------------------------------------------/ */

    // POST: add new employee
    @PostMapping("/employee/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee);
    }


    /* /---------------------------------------GET------------------------------------------/ */

    // GET: show all employee
    @GetMapping("/employee/getAllEmployees")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // GET: show employees that worked a specific amount of time
    @GetMapping("/employee/getEmployeeByHourlyRate")
    private List<Employee> getEmployeeByHourlyRate(@RequestParam String hourlyRate) {
        return employeeService.getEmployeeByHourlyRate(hourlyRate);
    }


    // GET: show all employee with a specific name
    @GetMapping("/employee/getEmployeeByName")
    private List<Employee> getEmployeeByName(@RequestParam String name) {
        return employeeService.getEmployeeByName(name);
    }


    /* /---------------------------------------DELETE------------------------------------------/ */

    // DELETE: delete employee body request
    @DeleteMapping("/employee/deleteEmployee")
    public void deleteEmployee(@RequestBody Employee employee) {
        employeeService.deleteEmployee(employee);
    }


    // DELETE: delete employee by name
    @DeleteMapping("/employee/deleteEmployeeByName")
    private void deleteEmployeeByName(@RequestParam String name) {
        employeeService.deleteEmployeeByName(name);
    }


    // DELETE: delete employee by ID
    @DeleteMapping("/employee/deleteEmployeeById")
    private void deleteEmployeeById(@RequestParam Employee id) {
        employeeService.deleteEmployeeById(id);
    }

    /* /---------------------------------------DELETE------------------------------------------/ */

    // PUT: update a employee name
    @PutMapping("/employee/updateEmployee")
    private void updateEmployeeDetails(@RequestParam Integer id, @RequestBody Employee employee) {
        employeeService.updateEmployeeDetails(id, employee);
    }
}
