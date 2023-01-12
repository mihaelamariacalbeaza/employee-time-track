package com.timeTrackApplication.timeTrackApplication.Service;

import com.timeTrackApplication.timeTrackApplication.Model.TimeTracking;
import com.timeTrackApplication.timeTrackApplication.Repository.TimeTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTrackService {

    @Autowired
    private TimeTrackRepository timeTrackRepository;


    /* /---------------------------------------POST------------------------------------------/ */

    // POST: add new time track for employee : SAVE
    public TimeTracking createTimeTracking(TimeTracking timeTracking) {

        return timeTrackRepository.save(timeTracking);
    }


    /* /---------------------------------------GET------------------------------------------/ */

    // GET: show all time track for all employee
    public List<TimeTracking> getAllEmployeeTimeTrack() {

        return timeTrackRepository.findAll();
    }

    // GET: show time track for an employee by employee id
    public List<TimeTracking> getAnEmployeeTimeTrackByEmployeeId(Integer idEmployee) {
        List<TimeTracking> timeTrackingList = getAllEmployeeTimeTrack();
        List<TimeTracking> returnedTimeTrackingList = new ArrayList<>();

        for (TimeTracking timeTracking : timeTrackingList) {
            if (timeTracking.getIdEmployee() == (idEmployee)) {
                returnedTimeTrackingList.add(timeTracking);
            }
        }
        return returnedTimeTrackingList;
    }

    // to do GET: hour and day worked


    /* /---------------------------------------DELETE------------------------------------------/ */

    // DELETE: delete Time Track body request
    public void deleteTimeTrack(TimeTracking timeTracking) {
        timeTrackRepository.delete(timeTracking);
    }


    // DELETE: delete Time Track by emoployee id
    public void deleteTimeTrackByEmployeeId(Integer idEmployee) {
        List<TimeTracking> timeTrackingList = getAllEmployeeTimeTrack();
        List<TimeTracking> returnedtimeTrackingList = new ArrayList<>();
        for (TimeTracking timeTracking : timeTrackingList) {
            if (timeTracking.getIdEmployee().equals(idEmployee)) {
                deleteTimeTrack(timeTracking);
            }
        }
    }


    // DELETE: delete Time Track by time track id
    public void deleteTimeTrackByTimeTrackId(Integer idTimeTrack) {
        List<TimeTracking> timeTrackingList = getAllEmployeeTimeTrack();
        for (TimeTracking timeTracking : timeTrackingList) {
            if (timeTracking.getIdTimeTrack().equals(idTimeTrack)) {
                deleteTimeTrack(timeTracking);
            }
        }
    }


    /* /---------------------------------------PUT------------------------------------------/ */

    public TimeTracking saveTimeTracking(TimeTracking timeTracking) {
        return timeTrackRepository.save(timeTracking);
    }

    // PUT: update a Time Track
    public TimeTracking updateEmployeeTimeTrack(Integer idTimeTrack, TimeTracking timeTracking) {
        TimeTracking timeTrackToUpdate = timeTrackRepository.findById(idTimeTrack).get();

        if (timeTracking.getIdEmployee().equals(timeTracking.getIdEmployee())) {
            timeTrackToUpdate.setIdEmployee((timeTracking.getIdEmployee()));
        }

        if (timeTracking.getPunchIn().equals(timeTracking.getPunchIn())) {
            timeTrackToUpdate.setPunchIn((timeTracking.getPunchIn()));
        }

        if (timeTracking.getPunchOut().equals(timeTracking.getPunchOut())) {
            timeTrackToUpdate.setPunchOut((timeTracking.getPunchOut()));
        }
        TimeTracking timeTracking1 = saveTimeTracking(timeTrackToUpdate);
        return timeTracking1;
    }

}