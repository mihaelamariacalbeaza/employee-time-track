package com.timeTrackApplication.timeTrackApplication.Repository;

import com.timeTrackApplication.timeTrackApplication.Model.TimeTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTrackRepository extends JpaRepository<TimeTracking, Integer> {
}
