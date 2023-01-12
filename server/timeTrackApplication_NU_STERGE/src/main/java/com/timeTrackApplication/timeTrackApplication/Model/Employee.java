package com.timeTrackApplication.timeTrackApplication.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EmployeeTable")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idEmployee")
    private List<TimeTracking> timeTrackingList;
    @Column(name = "name")
    private String name;
    @Column(name = "hourlyRate")
    private String hourlyRate;

}
