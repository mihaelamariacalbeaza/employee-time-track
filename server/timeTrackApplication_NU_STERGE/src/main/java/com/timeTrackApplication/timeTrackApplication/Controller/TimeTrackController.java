package com.timeTrackApplication.timeTrackApplication.Controller;


import com.timeTrackApplication.timeTrackApplication.Model.Employee;
import com.timeTrackApplication.timeTrackApplication.Model.TimeTracking;
import com.timeTrackApplication.timeTrackApplication.Service.TimeTrackService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@RestController
@CrossOrigin
@ComponentScan

// all the CRUD operations

public class TimeTrackController {

    @Autowired
    TimeTrackService timeTrackService;

    /* /---------------------------------------POST------------------------------------------/ */

        // CREATE: add new time track for employee
    @PostMapping("/timetrack/createTimeTracking/")
    public TimeTracking createTimeTracking(@RequestBody TimeTracking timeTracking) {
        return timeTrackService.createTimeTracking(timeTracking);
    }

    /* /---------------------------------------GET------------------------------------------/ */

        // GET: show all time track for all employee
    @RequestMapping("/timetrack/getAllEmployeeTimeTrack/")
    public List<TimeTracking> getAllEmployeeTimeTrack() {

        return timeTrackService.getAllEmployeeTimeTrack();
    }

        // GET: show time track for an employee by employee id
    @RequestMapping("/timetrack/getAnEmployeeTimeTrackByEmployeeId/{idEmployee}")
    public List<TimeTracking> getAnEmployeeTimeTrackByEmployeeID(@PathVariable Integer idEmployee) {

        return timeTrackService.getAnEmployeeTimeTrackByEmployeeId(idEmployee);
    }
    //---------------------------------
        // GET: hours worked
    ///@GetMapping("/timetrack/gethoursWorked/{IdEmployee}")
    //public long gethoursWorked(@PathVariable("IdEmployee") Integer IdEmployee, @RequestParam("from") @DateTimeFormat(pattern = "yy-MM-dd")LocalDate from, @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        //return ChronoUnit.DAYS.between(from, to);//days
        //return ChronoUnit.DAYS.between(from, to)*8;//hours


   // }


    /* /---------------------------------------DELETE------------------------------------------/ */

        // DELETE: delete Time Track body request
    @DeleteMapping("/timetrack/deleteTimeTrack/")
    public void deleteTimeTrack(@RequestBody TimeTracking timeTracking) {
        timeTrackService.deleteTimeTrack(timeTracking);
    }

        // DELETE: delete a time track by employee id
    @DeleteMapping("/timetrack/deleteTimeTrackByEmployeeId/")
    public void deleteTimeTrackByEmployeeId(@RequestParam Integer idEmployee) {
        timeTrackService.deleteTimeTrackByEmployeeId(idEmployee);
    }

        // DELETE: delete a time track by time track id
    @DeleteMapping("/timetrack/deleteTimeTrackByTimeTrackId/")
    public void deleteTimeTrackByTimeTrackId(@RequestParam Integer idTimeTrack) {
        timeTrackService.deleteTimeTrackByTimeTrackId(idTimeTrack);
    }

    /* /---------------------------------------PUT------------------------------------------/ */

        // PUT: update a Time Track
    @PutMapping("/timeTrack/updateEmployeeTimeTrack/")
    private void updateEmployeeTimeTrack(@RequestParam Integer idTimeTrack, @RequestBody TimeTracking timeTracking){
        timeTrackService.updateEmployeeTimeTrack(idTimeTrack, timeTracking);
    }




}
